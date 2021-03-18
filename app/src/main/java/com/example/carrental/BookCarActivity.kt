package com.example.carrental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carrental.Adapters.CarAdapter

class BookCarActivity : AppCompatActivity() {

    fun onClickBack(view: View) {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_car)
        supportActionBar?.hide()

        val currentUserID =
            getIntent().getStringExtra(MainActivity.EXTRA_VALUE_USER_ID_NAME).toString()
        val carDataset = Cars().loadCarList(this.resources)

        val adapter: CarAdapter =
            CarAdapter(this, carDataset)
        adapter.userId = currentUserID.toInt()
        val cars_offers: RecyclerView = findViewById<RecyclerView>(R.id.list_cars_offers)
        cars_offers.adapter = adapter
        cars_offers.layoutManager = LinearLayoutManager(this)

    }

    companion object {
        const val POS_CAR_IN_LIST = "CAR_POSITION"
    }
}