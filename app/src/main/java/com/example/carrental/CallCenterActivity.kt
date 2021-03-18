package com.example.carrental

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CallCenterActivity : AppCompatActivity() {

    companion object {
        const val PERMISSION_CALL_PHONE_REQUEST_CODE = 1
    }

    fun onClickBack(view: View) {

        finish()

    }

    fun makeCall() {

        val number = "515729809"
        val intent = Intent(Intent.ACTION_CALL)
        intent.setData(Uri.parse("tel:" + number))
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_center)
        supportActionBar?.hide()

        val btn_call = findViewById<ImageView>(R.id.btn_call)

        btn_call.setOnClickListener {

            try {

                if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

                    makeCall()

                } else {

                    // if user didnt give permisions till now
                    val permissions = arrayOf((Manifest.permission.CALL_PHONE))

                    requestPermissions(permissions, PERMISSION_CALL_PHONE_REQUEST_CODE)
                }

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {

            PERMISSION_CALL_PHONE_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    makeCall()

                } else {
                    Toast.makeText(this, getString(R.string.needed_permissions), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}