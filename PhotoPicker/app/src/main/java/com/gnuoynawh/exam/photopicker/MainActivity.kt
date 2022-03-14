package com.gnuoynawh.exam.photopicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val listView by lazy {
        findViewById<ListView>(R.id.listView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<String>()
        list.add("Galley")
        list.add("Galley With Selected")

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            when(position) {
                0 -> {
                    val intent = Intent(this, GalleryActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    val intent = Intent(this, GallerySelectedActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}