package com.example.applemarket

import java.text.DecimalFormat

open class SaveList {
    var saveList = mutableListOf<SaveList>()

    fun getDecimalFormat(number: String): String {
        val deciamlFormat = DecimalFormat("#,###")
        return deciamlFormat.format(number)
    }
}