package com.gnuoynawh.samples.sampleutilsapp.base

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnuoynawh.samples.sampleutilsapp.R
import com.gnuoynawh.samples.sampleutilsapp.ui.CalendarActivity
import com.gnuoynawh.samples.sampleutilsapp.ui.HtmlParseActivity
import com.gnuoynawh.samples.sampleutilsapp.ui.OpenChromeActivity
import com.gnuoynawh.samples.sampleutilsapp.ui.foldable.FoldableActivity
import com.gnuoynawh.samples.sampleutilsapp.ui.VersionCheckActivity
import com.gnuoynawh.samples.sampleutilsapp.ui.setting.LanguageActivity

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
        works.add(Work("폴더블 화면 구현", FoldableActivity::class.java))
        works.add(Work("캘린더/날짜 정리", CalendarActivity::class.java))
        works.add(Work("언어 변환", LanguageActivity::class.java))
        works.add(Work("Html 파싱", HtmlParseActivity::class.java))
        works.add(Work("크롬 실행시키기", OpenChromeActivity::class.java))
        adapter.notifyDataSetChanged()

    }

}