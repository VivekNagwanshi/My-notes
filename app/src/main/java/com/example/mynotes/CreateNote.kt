package com.example.mynotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.user.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CreateNote : AppCompatActivity() {
    private lateinit var title: EditText
    private lateinit var description: EditText
    lateinit var floatingButton: FloatingActionButton
    private lateinit var addNoteButton: Button
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var userImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_note)
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.title = "Create"
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        title = findViewById(R.id.NoteTitle)
        description = findViewById(R.id.NoteDescription)
        addNoteButton = findViewById(R.id.AddNoteButton)
//        floatingButton = findViewById(R.id.floatingActionButton)
//        userImage = findViewById(R.id.UserImage)

        addNoteButton.setOnClickListener {
            addNote()
        }
//        floatingButton.setOnClickListener {
//            // startActivity(Intent(this, this::class.java))
//            val intent = Intent()
//            intent.type = "image/*"
//            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//            intent.action = Intent.ACTION_GET_CONTENT
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 123)
//        }
    }

    private fun addNote() {
        val noteDescription: Int = description.text.toString().replace("\n", " ").split(" ").size
        val noteTitle: Int = title.text.toString().replace("\n", " ").split(" ").size
        if (noteTitle > 0 && noteDescription > 0) {
            val noteTitle = title.text.toString()
            val noteDescription = description.text.toString()
            if (inputCheck(noteTitle, noteDescription)) {
                val userData = UserData(0, noteTitle, noteDescription)
                mUserViewModel.addUser(userData)
                Toast.makeText(this, "Data added successfully ", Toast.LENGTH_SHORT).show()
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Data added Failed ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun inputCheck(noteTitle: String, noteDescription: String): Boolean {
        val descriptionCount: Int = description.text.toString().replace("\n", " ").split(" ").size
        val titleCount: Int = title.text.toString().replace("\n", " ").split(" ").size
        if (titleCount < 1 && descriptionCount < 10) {
            return false
        }
        return !(TextUtils.isEmpty(noteTitle) && TextUtils.isEmpty(noteDescription))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 123) {
            //userImage.setImageURI(data?.data) // handle chosen image
        }
    }
}