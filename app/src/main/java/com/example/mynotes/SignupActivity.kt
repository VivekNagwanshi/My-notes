package com.example.test_task_1.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import com.example.mynotes.MainActivity
import com.example.mynotes.R
import com.google.android.material.button.MaterialButtonToggleGroup

class SignupActivity : AppCompatActivity() {

    var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var editText: EditText
    private lateinit var userName: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var backBtn: ImageView

    private lateinit var toggalGroup: MaterialButtonToggleGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()


        userName = findViewById(R.id.Name)
        email = findViewById(R.id.Email)
        password = findViewById(R.id.edtPassword)
        backBtn = findViewById(R.id.backImage)
        backBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        /**
         * Login button when you click go to the login next Activity
         */
        val registerBtn = findViewById<AppCompatButton>(R.id.SignUpBtn)
        registerBtn.setOnClickListener {
            if (checkEditText()) {
                val intent = Intent(this, MainActivity::class.java)
             /*   val fullName = userName.text.toString() + " " + lastName.text.toString()
                val registerEmail = email.text.toString()
                val number = phoneNumber.text.toString()
                val dOB = editText.text.toString()
                val gender = phoneNumber.text.toString()
                val password = password.text.toString()
                intent.putExtra("Name", "Name - $fullName")
                intent.putExtra("Email", registerEmail)
                intent.putExtra("Number", number)
                intent.putExtra("DOB", dOB)
                intent.putExtra("Gender", gender)
                intent.putExtra("Password", password)*/
                startActivity(intent)
                finish()
            }
        }

    }

    private fun checkEditText(): Boolean {
        if (TextUtils.isEmpty(userName.text.toString())) {
            Toast.makeText(this, "Please Enter your first name !", Toast.LENGTH_SHORT).show()
            userName.error = "First Name is required"
            return false
        } else if (!(checkVailMail(email.text.toString()))) {
            email.error = "Email id is required"
            return false
        } else if (TextUtils.isEmpty(password.text.toString())) {
            Toast.makeText(this, "Please Enter your Password !", Toast.LENGTH_SHORT).show()
            password.error = "Password is required"
            return false
        } else {
            return true
        }
    }

    /**
     * check Email function your mail is valid or not

     */
    private fun checkVailMail(toString: String): Boolean {
        if (toString.isEmpty() || toString == ""
        ) {
            Toast.makeText(this, "Please Enter your Email", Toast.LENGTH_SHORT).show()
        } else {

            if (toString.matches(emailPattern.toRegex())) {

                return true

            } else {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
            }

        }
        return false
    }
}