package com.example.carrental

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.carrental.MainActivity.Companion.EXTRA_VALUE_USER_ID_NAME
import com.example.carrental.model.User

class EditAccountActivity : AppCompatActivity() {

    enum class EditType {
        NAME, SURNAME, NICKNAME, PASSWORD
    }

    var idd: Int = -1

    fun onClickBack(view: View) {
        finish()
    }

    fun onClickBackToSettings(view: View) {
        setContentView(R.layout.activity_edit_account)
    }

    fun setUserInfo(user: User) {
        var name_info: TextView = findViewById(R.id.name_info)
        var nickname_info: TextView = findViewById(R.id.nickname_info)
        var surname_info: TextView = findViewById(R.id.surname_info)
        var password_info: TextView = findViewById(R.id.password_info)

        name_info.text = user?.name
        surname_info.text = user?.surname
        nickname_info.text = user?.nickname
        password_info.text = user?.password
    }

    private fun updateInformation(editType: EditType) {
        val btn_confirmNewChange = findViewById<Button>(R.id.btn_confirmNewChange)
        btn_confirmNewChange.setOnClickListener {
            val new_info = findViewById<EditText>(R.id.new_info)

            val usertemp = Users(this).getUser(idd)

            when (editType) {
                EditType.NAME -> usertemp?.name = new_info.text.toString()
                EditType.SURNAME -> usertemp?.surname = new_info.text.toString()
                EditType.NICKNAME -> usertemp?.nickname = new_info.text.toString()
                EditType.PASSWORD -> usertemp?.password = new_info.text.toString()
            }

            Users(this).updateUser(usertemp!!)

            setContentView(R.layout.activity_edit_account)
            setUserInfo(usertemp)
        }
    }

    fun deleteAccount(view: View) {
        AlertDialog.Builder(this).setMessage(resources.getString(R.string.delete_confirm))
            .setPositiveButton(resources.getString(R.string.yes_answer)) { dialog, id ->
                Users(this).deleteUser(idd)

                try {
                    finish()
                    Toast.makeText(this, getString(R.string.success_delete), Toast.LENGTH_LONG)
                        .show()
                } catch (e: Exception) {
                    Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                }
            }
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_account)
        supportActionBar?.hide()

        idd = getIntent().getExtras()?.get(EXTRA_VALUE_USER_ID_NAME).toString().toInt();
        val user = Users(this).getUser(idd)

        setUserInfo(user!!)

        // edit name
        val btn_edit_name = findViewById<ImageButton>(R.id.btn_edit_name)
        btn_edit_name.setOnClickListener {

            setContentView(R.layout.change_item_account)

            updateInformation(EditType.NAME)
        }

        // edit surname
        val btn_edit_surname = findViewById<ImageButton>(R.id.btn_edit_surname)
        btn_edit_surname.setOnClickListener {

            setContentView(R.layout.change_item_account)

            updateInformation(EditType.SURNAME)
        }

        // edit surname
        val btn_edit_nickname = findViewById<ImageButton>(R.id.btn_edit_nickname)
        btn_edit_nickname.setOnClickListener {

            setContentView(R.layout.change_item_account)

            updateInformation(EditType.NICKNAME)
        }

        // edit surname
        val btn_edit_password = findViewById<ImageButton>(R.id.btn_edit_password)
        btn_edit_password.setOnClickListener {

            setContentView(R.layout.change_item_account)

            updateInformation(EditType.PASSWORD)
        }
    }
}
