package com.example.carrental.model

import androidx.annotation.DrawableRes

data class Car(
    val id: Int,
    val carname: String,
    val carmodel: String,
    @DrawableRes val image: Int?,
    val price: String,
    val availability: String,
    val description: String
) {

}