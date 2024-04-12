package com.example.applemarket

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemEntity(
    val name: String,
    val description: Int,
    val seller: String,
    val price : String,
    val address: String,
    var like: Int,
    var chat: Int,
    val image: Int,
    val key: Int
) : Parcelable