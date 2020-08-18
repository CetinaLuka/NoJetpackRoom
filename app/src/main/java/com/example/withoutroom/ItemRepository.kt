package com.example.withoutroom

import androidx.lifecycle.LiveData

class ItemRepository(private val dbHelper: DataBaseHelper) {
    val allItems: LiveData<List<Item>> = dbHelper.readAllData()
    suspend fun insert(item: String){
        dbHelper.addItem(item)
    }
}