package com.example.withoutroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application): AndroidViewModel(application) {
    private val repository: ItemRepository
    val allItems: LiveData<List<Item>>
    init {
        val dbHelper = DataBaseHelper(application)
        repository = ItemRepository(dbHelper)
        allItems = repository.allItems
    }
    fun insert(item: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(item)
    }
}