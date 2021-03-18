package com.example.carrental.Adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.carrental.*
import com.example.carrental.model.Car
import com.example.carrental.model.Order

class OrderAdapter(
    private val context: UserOrdersActivity,
    private val cardataset: ArrayList<Car>,
    private val userOrder: List<Order>
) : RecyclerView.Adapter<OrderAdapter.OrderHolder>() {

    var userId: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.single_order_item, parent, false)

        return OrderHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        val item = cardataset[position]

        holder.tvCarName.text = item.carname + " " + item.carmodel
        holder.imageButton.setImageResource(item.image!!)

        holder.itemView.setOnClickListener {

            AlertDialog.Builder(context).setMessage(context.getString(R.string.delete_confirm))
                .setPositiveButton(context.getString(R.string.yes_answer)) { dialog, id ->
                    for (item in userOrder) {
                        if (cardataset[position].id == item.carID) {
                            Orders(context).deleteOrder(item.id)
                            cardataset.remove(cardataset[position])
                        }
                    }
                    this!!.notifyDataSetChanged()
                }
                .show()
        }
    }


    override fun getItemCount() = cardataset.size

//    override fun getItemId(p0: Int): Long {
//
//        return cardataset.get(p0).id.toLong()
//    }

    // TODO CZY MOGE USUNAC POWYZSZE?

    class OrderHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCarName: TextView = view.findViewById(R.id.tvCarName)
        val imageButton: ImageView = view.findViewById(R.id.imgCarDetails)
    }


}