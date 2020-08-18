package com.example.withoutroom

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) { db.execSQL(SQL_CREATE_ENTRIES) }
    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) { db.execSQL(SQL_DELETE_ENTRIES) }
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ItemDatabase.db"
        private const val TABLE_NAME = "items_table"
        private const val COLUMN_ID = "id"
        private const val COLUMN_ITEM = "item"
        private const val SQL_CREATE_ENTRIES =
            "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_ITEM TEXT)"
        private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
    fun addItem(item: String){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ITEM, item)
        }
        val newRowId = db.insert(TABLE_NAME, null, values)
    }
    fun readAllData(): LiveData<List<Item>>{
        val db = this.readableDatabase
        val projection = arrayOf(COLUMN_ID, COLUMN_ITEM)
        val sortOrder = "$COLUMN_ID DESC"
        val cursor = db.query(TABLE_NAME, projection, null, null, null, null, sortOrder)
        val liveData = MutableLiveData<List<Item>>()
        val items = mutableListOf<Item>()
        with(cursor){
            while (moveToNext()){
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val item = getString(getColumnIndexOrThrow(COLUMN_ITEM))
                items.add(Item(id, item))
            }
        }
        liveData.postValue(items)
        return liveData
    }

}