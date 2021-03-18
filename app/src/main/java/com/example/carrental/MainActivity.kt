package com.example.carrental

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var slideDown: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        slideDown = AnimationUtils.loadAnimation(this, R.anim.slide_down)
        val welcomeImage = findViewById<ImageView>(R.id.imgLogoClassic)
        welcomeImage.startAnimation(slideDown)

        val btnLog = findViewById<Button>(R.id.btnSign_in)
        val btnReg = findViewById<Button>(R.id.btnSignUp)
        var nicknameTextValue = findViewById<EditText>(R.id.et_login_nick)
        var passwordTextValue = findViewById<EditText>(R.id.et_login_pass)

        fun verifyUser() {

            val nickname = nicknameTextValue.text.toString()
            val password = passwordTextValue.text.toString()
            if (Users(this).checkUserExists(nickname, password)) {

                val accountsIntent = Intent(this, LoggedUserActivity::class.java)
                accountsIntent.putExtra("nickname", nickname)

                val id = Users(this).getUserByNameAndPassword(nickname, password)

                if (id != -1) {
                    accountsIntent.putExtra(Companion.EXTRA_VALUE_USER_ID_NAME, id.toString())
                }

                startActivity(accountsIntent)

            } else {
                Toast.makeText(this, getString(R.string.account_not_exists), Toast.LENGTH_LONG).show()
            }
        }

        btnLog.setOnClickListener {

            try {
                verifyUser()
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

        btnReg.setOnClickListener {

            try {
                val intent = Intent(this, Register_In_System_Activity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
            }
        }
    }

    companion object {
        const val EXTRA_VALUE_USER_ID_NAME = "ID"
    }

}