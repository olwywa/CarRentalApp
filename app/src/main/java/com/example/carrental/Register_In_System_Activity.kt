package com.example.carrental

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Register_In_System_Activity : AppCompatActivity() {

    var checkTermsResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_in_system)

        val btnSubReg = findViewById<Button>(R.id.btn_submit_registration)
        val nameTextValue = findViewById<EditText>(R.id.et_name)
        val surnameTextValue = findViewById<EditText>(R.id.et_surname)
        val nicknameTextValue = findViewById<EditText>(R.id.et_nickname)
        val passwordTextValue = findViewById<EditText>(R.id.et_password1)
        val password2TextValue = findViewById<EditText>(R.id.et_password2)
        val checkTerms = findViewById<CheckBox>(R.id.checkTerms)

        checkTerms.setOnClickListener {

            if (checkTerms.isChecked)
                checkTermsResult = true
            else
                checkTermsResult = false
        }

        btnSubReg.setOnClickListener {

            try {

                val name: String = nameTextValue.text.toString()
                val surname: String = surnameTextValue.text.toString()
                val nickname: String = nicknameTextValue.text.toString()
                val password: String = passwordTextValue.text.toString()
                val password2: String = password2TextValue.text.toString()

                if (name.isNotBlank() && surname.isNotBlank() && nickname.isNotBlank() && password.isNotBlank() && password2.isNotBlank()
                    && password.equals(password2) && checkTermsResult
                ) {

                    Users(this).createUser(name, surname, nickname, password)

                    finish()

                } else {
                    Toast.makeText(this, resources.getString(R.string.not_null), Toast.LENGTH_LONG)
                        .show()
                }

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}