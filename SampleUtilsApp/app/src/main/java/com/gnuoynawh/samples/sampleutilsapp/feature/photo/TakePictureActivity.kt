package com.gnuoynawh.samples.sampleutilsapp.feature.photo

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.gnuoynawh.samples.sampleutilsapp.R

class TakePictureActivity: AppCompatActivity(), View.OnClickListener {

    private val btnAddThumb: AppCompatButton by lazy {
        findViewById(R.id.btn_add_thumb)
    }

    private val btnRemoveThumb: AppCompatButton by lazy {
        findViewById(R.id.btn_remove_thumb)
    }

    private val ivThumb: AppCompatImageView by lazy {
        findViewById(R.id.iv_thumb)
    }

    override fun onClick(v: View) {

        when(v.id) {
            R.id.btn_add_thumb -> addImage()
            R.id.btn_remove_thumb -> removeImage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_picture)

        btnAddThumb.setOnClickListener(this)
        btnRemoveThumb.setOnClickListener(this)
    }

    private fun addImage() {
        val intent = Intent(this, CameraActivity::class.java)
        addActivityResultLauncher.launch(intent)
    }

    private fun removeImage() {
        ivThumb.setImageResource(0)
    }

    private var addActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {

            val path = it.data?.getStringExtra("filePath")
            val bitmap = BitmapFactory.decodeFile(path)
            ivThumb.setImageBitmap(bitmap)

        }
    }
}