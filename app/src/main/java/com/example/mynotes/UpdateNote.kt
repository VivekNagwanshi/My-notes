package com.example.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.user.UserViewModel


class UpdateNote : AppCompatActivity() {

    private lateinit var uNoteTitle: EditText
    private lateinit var uNoteDescription: EditText
    private lateinit var uNoteButton: Button
    private lateinit var mUserViewModel: UserViewModel
    private var currentUserId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = "Update"

        uNoteTitle = findViewById(R.id.UNoteTitle)
        uNoteDescription = findViewById(R.id.UNoteDescription)
       // uNoteButton = findViewById(R.id.UpdateNoteButton)

        val userId = intent.getIntExtra("userId", 0)
        val displayTitle = intent.getStringExtra("title")
        val displayDescription = intent.getStringExtra("description")
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        uNoteDescription.setText(displayDescription)
        uNoteTitle.setText(displayTitle)
//        uUserId.setText(userId)
        currentUserId = userId

       /* uNoteButton.setOnClickListener {
            updateNote()
        }*/

    }

    private fun updateNote() {
        val currentTitle = uNoteTitle.text.toString()
        val currentDescription = uNoteDescription.text.toString()
        var flag = false
        if (inputCheck(currentTitle, currentDescription)) {
            val updateNote = UserData(currentUserId, currentTitle, currentDescription)
            mUserViewModel.updateUser(updateNote)
            Toast.makeText(this, "Data updated Successfully", Toast.LENGTH_SHORT).show()
            flag = true
        } else {
            Toast.makeText(this, "Data updated Failed", Toast.LENGTH_SHORT).show()
            flag = false
        }
        if (flag) {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun inputCheck(noteTitle: String, noteDescription: String): Boolean {
        return !(TextUtils.isEmpty(noteTitle) && TextUtils.isEmpty(noteDescription))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemDelete) {
            deleteUserData()
        }
        if (item.itemId == R.id.itemUpdate) {
            updateNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUserData() {
        val currentTitle = uNoteTitle.text.toString()
        val currentDescription = uNoteDescription.text.toString()
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            val updateNote = UserData(currentUserId, currentTitle, currentDescription)
            mUserViewModel.deleteUser(updateNote)
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        builder.setNegativeButton("No") { _, _ ->
        }
        builder.setTitle("Delete  ?")
        builder.setMessage("Are you sure you want to delete ")
        builder.create().show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}