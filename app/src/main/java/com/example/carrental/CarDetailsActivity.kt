package com.example.carrental

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.carrental.model.Car
import com.example.carrental.model.Order

class CarDetailsActivity : AppCompatActivity() {

    lateinit var car : Car
    fun onClickBack(view: View) {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details)
        supportActionBar?.hide()

        val btn_bookThatCar = findViewById<Button>(R.id.btn_bookThatCar)
        val cars: List<Car> = Cars().loadCarList(this.resources)
        val pos = getIntent().getExtras()?.get(BookCarActivity.POS_CAR_IN_LIST).toString().toInt();
        val idUser = getIntent().getExtras()?.get(MainActivity.EXTRA_VALUE_USER_ID_NAME).toString().toInt();
        car = cars[pos]

        var tv_carBrandName = findViewById<TextView>(R.id.tv_carBrandName)
        var tv_carAvail = findViewById<TextView>(R.id.tv_car_available)
        var tv_car_description = findViewById<TextView>(R.id.tv_car_description)
        var tv_car_price = findViewById<TextView>(R.id.tv_car_price)
        var imageCar = findViewById<ImageView>(R.id.img_car)

        tv_carBrandName.text = car.carname
        imageCar.setImageResource(car.image!!)
        tv_carAvail.text = getString(R.string.tv_availableDates) + " " + car.availability
        tv_car_description.text = car.description
        tv_car_price.text = getString(R.string.tv_pricePerDay) + " " + car.price

        btn_bookThatCar.setOnClickListener{
            AlertDialog.Builder(this).setMessage(resources.getString(R.string.delete_confirm))
                .setPositiveButton(resources.getString(R.string.yes_answer)) { dialog, id ->
                    Orders(this).createOrder(car.id,idUser)
                    val orders : List<Order> = Orders(this).getOrders()
                }
                .show()

        }

    }
}