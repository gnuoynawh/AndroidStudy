package com.gnuoynawh.samples.sampleutilsapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val works = ArrayList<Work>()
    private lateinit var adapter: WorkAdapter

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerview)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        setData()

    }

    private fun initRecyclerView() {
        adapter = WorkAdapter(this, works)
        adapter.setOnItemClickListener(object : WorkAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val intent = Intent(this@MainActivity, works[position].cls)
                startActivity(intent)
                overridePendingTransition(0,0)
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData() {

        works.add(Work("버전체크", VersionCheckActivity::class.java))
        adapter.notifyDataSetChanged()

    }

}