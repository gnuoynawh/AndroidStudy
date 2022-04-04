package com.gnuoynawh.exam.openchrome

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton


class MainActivity : AppCompatActivity() {

    private val urlString = "http://m.naver.com"

    private val btnBrowser: AppCompatButton by lazy {
        findViewById(R.id.btn_open_browser)
    }

    private val btnChrome: AppCompatButton by lazy {
        findViewById(R.id.btn_open_chrome)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBrowser.setOnClickListener {
            openBrowser()
        }

        btnChrome.setOnClickListener {
            openChrome()
        }
    }

    private fun openBrowser() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    private fun openChrome() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            intent.setPackage(null)
            startActivity(intent)
        }
    }
}