package com.example.withoutroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        button_save.setOnClickListener {
            if(!TextUtils.isEmpty(edit_item.text)){
                itemViewModel.insert(edit_item.text.toString())
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }
}