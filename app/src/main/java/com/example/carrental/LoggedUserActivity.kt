package com.example.carrental

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.carrental.MainActivity.Companion.EXTRA_VALUE_USER_ID_NAME

class LoggedUserActivity : AppCompatActivity() {

    lateinit var currentUserID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged_user)
        supportActionBar?.hide()

        currentUserID = getIntent().getStringExtra(EXTRA_VALUE_USER_ID_NAME).toString()

        // -- NAVIGATION BAR  --
        val edit_profile: CardView = findViewById(R.id.card_view_edit_profile)
        val my_orders: CardView = findViewById(R.id.card_view_my_orders)
        val book_cars: CardView = findViewById(R.id.card_view_booking)
        val call_center: CardView = findViewById(R.id.card_view_callcenter)
        val log_out: LinearLayout = findViewById(R.id.btn_logout)

        // -- NAVIGATION CLICKING LISTENERS
        edit_profile.setOnClickListener {
            val intent = Intent(this, EditAccountActivity::class.java)
            try {
                intent.putExtra(MainActivity.EXTRA_VALUE_USER_ID_NAME, currentUserID)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
        book_cars.setOnClickListener {
            intent = Intent(this, BookCarActivity::class.java)
            try {
                intent.putExtra(MainActivity.EXTRA_VALUE_USER_ID_NAME, currentUserID)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }
        call_center.setOnClickListener {
            intent = Intent(this, CallCenterActivity::class.java)
            try {
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }

        my_orders.setOnClickListener {
            intent = Intent(this, UserOrdersActivity::class.java)
            try {
                intent.putExtra(MainActivity.EXTRA_VALUE_USER_ID_NAME, currentUserID)
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }
        }


        log_out.setOnClickListener {

            AlertDialog.Builder(this).setMessage(resources.getString(R.string.delete_confirm))
                .setPositiveButton(resources.getString(R.string.yes_answer)) { dialog, id ->

                    finish()
                }
                .show()
        }
        // -- END NAVIGATION BAR


    }
}