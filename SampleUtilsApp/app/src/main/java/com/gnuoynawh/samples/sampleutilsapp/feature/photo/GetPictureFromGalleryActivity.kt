package com.gnuoynawh.samples.sampleutilsapp.feature.photo

import android.R.attr.data
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.gnuoynawh.samples.sampleutilsapp.R


class GetPictureFromGalleryActivity: AppCompatActivity(), View.OnClickListener {

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
        setContentView(R.layout.activity_get_picture)

        btnAddThumb.setOnClickListener(this)
        btnRemoveThumb.setOnClickListener(this)
    }

    private fun addImage() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_MIME_TYPES, arrayOf("image/jpeg", "image/png"))
        addActivityResultLauncher.launch(intent)

    }

    private fun removeImage() {
        ivThumb.setImageResource(0)
    }

    private var addActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {

            ivThumb.setImageURI(it.data?.data)

        }
    }
}