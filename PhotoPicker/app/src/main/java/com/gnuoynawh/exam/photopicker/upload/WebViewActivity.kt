package com.gnuoynawh.exam.photopicker.upload

import android.Manifest
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.gnuoynawh.exam.photopicker.R

class WebViewActivity : AppCompatActivity() {

    private val webView by lazy {
        findViewById<WebView>(R.id.webView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
    }





}