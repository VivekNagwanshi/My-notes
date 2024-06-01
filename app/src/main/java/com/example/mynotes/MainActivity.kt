package com.example.mynotes

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.user.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var mUserViewModel: UserViewModel
    lateinit var shared: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "My Notes"
        val actionbarTitle = getResources().getIdentifier("action_bar_title", "id", "android");
        /* findViewById<View>(actionbarTitle).setOnClickListener{
             Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
         }*/
        shared = getSharedPreferences("LoginFile", MODE_PRIVATE)
        shared.edit().putBoolean(isLaunch, true).apply()
        floatingActionButton = findViewById(R.id.FloatingButton)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = MyAdapter(this)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.realAllData.observe(this) { userData ->
            adapter.setData(userData)
        }
        floatingActionButton.setOnClickListener {
            val intent = Intent(this, CreateNote::class.java)
            startActivity(intent)
        }
    }

    fun onItemClicked(userData: UserData) {
        val intent = Intent(this, UpdateNote::class.java)
        intent.putExtra("userId", userData.userId)
        intent.putExtra("description", userData.description)
        intent.putExtra("title", userData.title)
        startActivity(intent)
    }
    /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.menu_item, menu)
         return super.onCreateOptionsMenu(menu)

     }
     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
             R.id.about -> startActivity(Intent(this, AboutActivity::class.java))
         }
         return super.onOptionsItemSelected(item)
     }*/
}