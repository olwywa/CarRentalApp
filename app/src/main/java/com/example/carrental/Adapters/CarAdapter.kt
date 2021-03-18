package com.example.carrental.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.carrental.BookCarActivity
import com.example.carrental.CarDetailsActivity
import com.example.carrental.MainActivity
import com.example.carrental.R
import com.example.carrental.model.Car

class CarAdapter(private val context: Context, private val dataset: List<Car>) :
    RecyclerView.Adapter<CarAdapter.CarHolder>() {

    var userId: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.book_car_item, parent, false)

        return CarHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        val item = dataset[position]
        val sCarPrice: String = context.getString(R.string.tv_pricePerDay)
        val sAvailability = context.getString(R.string.tv_availableDates)

        holder.tvCarName.text = item.carname + " " + item.carmodel
        holder.tvCarPrice.text = sCarPrice + " " + item.price
        holder.imageButton.setImageResource(item.image!!)
        holder.tvAvailability.text = sAvailability + " " + item.availability

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CarDetailsActivity::class.java)
            try {
                intent.putExtra(BookCarActivity.POS_CAR_IN_LIST, position.toString())
                intent.putExtra(MainActivity.EXTRA_VALUE_USER_ID_NAME, userId.toString())
                context.startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun getItemCount() = dataset.size

    class CarHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCarName: TextView = view.findViewById(R.id.tvCarName)
        val tvCarPrice: TextView = view.findViewById(R.id.tvCarPrice)
        val imageButton: ImageView = view.findViewById(R.id.imgCarDetails)
        val tvAvailability: TextView = view.findViewById(R.id.tvAvailability)
    }
}