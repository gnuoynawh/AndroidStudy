package com.gnuoynawh.samples.sampleutilsapp.base

import android.Manifest.*
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnuoynawh.samples.sampleutilsapp.R
import com.gnuoynawh.samples.sampleutilsapp.feature.photo.GetPictureFromGalleryActivity
import com.gnuoynawh.samples.sampleutilsapp.feature.photo.TakePictureActivity
import com.gnuoynawh.samples.sampleutilsapp.ui.OpenChromeActivity
import com.gnuoynawh.samples.sampleutilsapp.ui.PopupTestActivity
import com.gnuoynawh.samples.sampleutilsapp.ui.VersionCheckActivity

class MainActivity : AppCompatActivity() {

    var permissions = arrayOf(
        permission.READ_EXTERNAL_STORAGE,
        permission.WRITE_EXTERNAL_STORAGE,
        permission.CAMERA
    )

    private val works = ArrayList<Work>()
    private lateinit var adapter: WorkAdapter

    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerview)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()

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

        works.add(Work("팝업 테스트", PopupTestActivity::class.java))
        works.add(Work("사진 - 카메라 촬영", TakePictureActivity::class.java))
        works.add(Work("사진 - 갤러리에서 가져오기", GetPictureFromGalleryActivity::class.java))
        works.add(Work("버전체크", VersionCheckActivity::class.java))
        works.add(Work("크롬 실행시키기", OpenChromeActivity::class.java))

        // works.add(Work("폴더블 화면 구현", FoldableActivity::class.java))
        // works.add(Work("캘린더/날짜 정리", CalendarActivity::class.java))
        // works.add(Work("언어 변환", LanguageActivity::class.java))
        // works.add(Work("Html 파싱", HtmlParseActivity::class.java))

        adapter.notifyDataSetChanged()

    }

    private fun checkPermission() {
        if ((checkSelfPermission(permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            || (checkSelfPermission(permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            || (checkSelfPermission(permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {

            if (shouldShowRequestPermissionRationale(permission.WRITE_EXTERNAL_STORAGE)
                || shouldShowRequestPermissionRationale(permission.READ_EXTERNAL_STORAGE)
                || shouldShowRequestPermissionRationale(permission.CAMERA)) {
                    Toast.makeText(this, "이미지를 가져오기 위해서 카메라 및 저장소 접근 권한이 필요합니다.", Toast.LENGTH_SHORT).show()
            }

            requestPermissions(permissions, 2000)
        }
    }
}