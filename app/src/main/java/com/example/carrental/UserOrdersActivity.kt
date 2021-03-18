package com.example.carrental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carrental.Adapters.OrderAdapter
import com.example.carrental.model.Car

class UserOrdersActivity : AppCompatActivity() {

    lateinit var currentUserID: String

    fun onClickBack(view: View) {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_orders)
        supportActionBar?.hide()

        currentUserID = getIntent().getStringExtra(MainActivity.EXTRA_VALUE_USER_ID_NAME).toString()

        val orderUserList = Orders(this).getOrdersByUserID(currentUserID)

        val carDataset = Cars().loadCarList(this.resources)

        val carResultsList: ArrayList<Car> = ArrayList<Car>()

        for (item in orderUserList) {
            for (item_car in carDataset) {
                if (item_car.id == item.carID) {
                    carResultsList.add(item_car)
                }
            }
        }

        val adapter: OrderAdapter = OrderAdapter(this, carResultsList, orderUserList)
        adapter.userId = currentUserID.toInt()
        val cars_offers: RecyclerView = findViewById<RecyclerView>(R.id.list_order_cars)
        cars_offers.adapter = adapter
        cars_offers.layoutManager = LinearLayoutManager(this)
    }
}