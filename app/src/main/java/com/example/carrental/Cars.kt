package com.example.carrental

import android.content.res.Resources
import com.example.carrental.model.Car

class Cars {

    fun loadCarList(resources: Resources): List<Car> {
        return listOf<Car>(
            Car(
                id = 1,
                carname = resources.getString(R.string.audi_name),
                carmodel = resources.getString(R.string.audi_model),
                price = resources.getString(R.string.audi_price),
                image = R.drawable.audia6,
                description = resources.getString(R.string.description_audi),
                availability = resources.getString(R.string.audi_availability)
            ),
            Car(
                id = 2,
                carname = resources.getString(R.string.bmw_name),
                carmodel = resources.getString(R.string.bmw_model),
                price = resources.getString(R.string.bmw_price),
                image = R.drawable.bmwm3,
                description = resources.getString(R.string.description_bmw),
                availability = resources.getString(R.string.bmw_availability)
            ),
            Car(
                id = 3,
                carname = resources.getString(R.string.multipla_name),
                carmodel = resources.getString(R.string.multipla_model),
                price = resources.getString(R.string.multipla_price),
                image = R.drawable.multipla,
                description = resources.getString(R.string.description_multi),
                availability = resources.getString(R.string.multipla_availability)
            ),
            Car(
                id = 4,
                carname = resources.getString(R.string.fordfocus_name),
                carmodel = resources.getString(R.string.fordfocus_model),
                price = resources.getString(R.string.fordfocus_price),
                image = R.drawable.fordfocusmk2,
                description = resources.getString(R.string.description_fordfocus),
                availability = resources.getString(R.string.ff_availability)
            ),
            Car(
                id = 5,
                carname = resources.getString(R.string.toyota_name),
                carmodel = resources.getString(R.string.toyota_model),
                price = resources.getString(R.string.toyota_price),
                image = R.drawable.toyotacorolla,
                description = resources.getString(R.string.description_toyota),
                availability = resources.getString(R.string.toyota_availability)
            )
        )
    }
}