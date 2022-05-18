package com.gnuoynawh.exam.cameraexam

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.nio.ByteBuffer
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    /*
        https://stackoverflow.com/questions/70164601/android-camerax-image-capture-onimagesaved-never-runs
        https://stackoverflow.com/questions/68008829/all-images-taken-with-android-camerax-have-incorrect-orientation-in-their-exif-d
        https://stackoverflow.com/questions/66994291/imagecapture-takepicture-saved-image-convert-to-bitmap
     */
    val listView: ListView by lazy {
        findViewById(R.id.listView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}