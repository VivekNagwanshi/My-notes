package com.example.test_task_1.auth

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.mynotes.MainActivity
import com.example.mynotes.R
import com.example.mynotes.databinding.ActivityLoginBinding
import com.example.mynotes.isLaunch
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    var emailPattern = "[a-zA-Z]+[0-9._-]+@[a-z]+\\.+[a-z]+"
    val passPatter = ("^" +
            "(?=.*[@#$%^&+=])" +     // at least 1 special character
            "(?=\\S+$)" +            // no white spaces
            ".{4,}" +                // at least 4 characters
            "$")
    val upperCase = ".*[A-Z].*"
    val lowerCase = ".*[a-z].*"
    val specialChar = ".*[@#$%^&+=].*"
    val noSpaces = "(?=\\S+$)"
    val ThreeChar = ".*[0-9].*"
    lateinit var PasswordText: EditText
    lateinit var email: EditText
    private lateinit var loginBtn: Button
    private lateinit var signUpText: TextView
    lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityLoginBinding
    private lateinit var emailText: TextInputEditText
    private var passwordFlag = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        supportActionBar?.hide()
        sharedPreferences = getSharedPreferences("LoginFile", MODE_PRIVATE)

        if (sharedPreferences.getBoolean(isLaunch, false)) {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        loginBtn = findViewById(R.id.LoginBtn)
        email = findViewById(R.id.edtEmail)
        PasswordText = findViewById(R.id.edtPassword)
        signUpText = findViewById(R.id.signUpText)
        val getEmailId: String = email.text.toString()


        /**
         * this is for password hide and show
         */
        binding.edtPassword.setOnTouchListener { v, event ->
            val DRAWABLE_RIGHT = 2
            if (event.getAction() === MotionEvent.ACTION_UP) {
                if (event.getRawX() >= binding.edtPassword.getRight() - binding.edtPassword.getCompoundDrawables()
                        .get(DRAWABLE_RIGHT).getBounds().width()
                ) {
                    if (passwordFlag) {
                        passwordFlag = false
                        binding.edtPassword.setTransformationMethod(PasswordTransformationMethod())
                    } else {
                        passwordFlag = true
                        binding.edtPassword.setTransformationMethod(null)
                    }
                    return@setOnTouchListener true
                }
            }
            false
        }

        loginBtn.setOnClickListener {
            if (checkVailMail() && checkVailPass()) {

                val intent = Intent(this, MainActivity::class.java)
                val message = email.text.toString()
                intent.putExtra("LoginEmail", message)
                startActivity(intent)
                finish()
            }
            if (checkVailMail()) {
            } else {
                YoYo.with(Techniques.Tada)
                    .duration(500)
                    .repeat(0)
                    .playOn(binding.edtEmail)
                email.setText("")
            }
            if (checkVailPass()) {
            } else {
                YoYo.with(Techniques.Tada)
                    .duration(500)
                    .repeat(0)
                    .playOn(binding.edtPassword)
                PasswordText.setText("")
            }

        }

        signUpText.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            finish()

        }


    }

    private fun checkVailPass(): Boolean {
        val passwordInput: String = PasswordText.text.toString().trim()

        if (passwordInput.isEmpty() || email.equals("") ||
            email == null
        ) {
            Toast.makeText(this, "Please Enter your Password", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!passwordInput.matches(upperCase.toRegex())) {
            PasswordText.error = "Password Must contain upperCase character"
            return false
        }
        if (!passwordInput.matches(lowerCase.toRegex())) {
            PasswordText.error = "Password Must contain lowerCase character"
            return false
        }
        if (!passwordInput.matches(specialChar.toRegex())) {
            PasswordText.error = "Password Must contain specialChar character"
            return false
        }
        if (!passwordInput.matches(ThreeChar.toRegex())) {
            PasswordText.error = "Password Must contain Numeric "
            return false
        }

        if (passwordInput.length < 8) {
            PasswordText.error = "Password Must contain 8 character"
            return false
        }
        if (!passwordInput.matches(passPatter.toRegex())) {
            PasswordText.error = "Password Invalid"
            return false
        }

        return true
    }

    private fun checkVailMail(): Boolean {
        if (email.length() == 0 || email.equals("") ||
            email == null
        ) {
            Toast.makeText(this, "Please Enter your Email", Toast.LENGTH_SHORT).show()
        } else {

            if (email.text.toString().matches(emailPattern.toRegex())) {

                return true

            } else {
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()
            }

        }
        return false
    }


}
