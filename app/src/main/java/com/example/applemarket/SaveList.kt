package com.example.applemarket

import java.text.DecimalFormat

open class SaveList {
    fun plugIn() : List<SaveEntity> {
        val saveList = mutableListOf<SaveEntity>()
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 1))
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 2))
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 3))
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 4))
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 5))
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 6))
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 7))
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 8))
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 9))
        saveList.add(SaveEntity(likeCount = 0, chatCount = 0, likeStatus = false, key = 10))
        return saveList
    }

}