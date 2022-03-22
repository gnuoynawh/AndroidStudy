package com.gnuoynawh.exam.cameraexam

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.File
import java.nio.ByteBuffer

class CaptureActivity : AppCompatActivity() {

    private val tvTitle: AppCompatTextView by lazy {
        findViewById(R.id.tvTitle);
    }

    private val previewView: PreviewView by lazy {
        findViewById(R.id.previewView)
    }

    private val ivResult: AppCompatImageView by lazy {
        findViewById(R.id.ivResult);
    }

    private val btnTake: AppCompatButton by lazy {
        findViewById(R.id.btnTake)
    }

    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        permissionCheck()
        outputDirectory = getOutputDirectory()

        tvTitle.text = "Capture"
        btnTake.setOnClickListener {
            takePhoto()
        }
    }
    private fun takePhoto() {

        // Get a stable reference of the modifiable image capture use case
        imageCapture?.takePicture(ContextCompat.getMainExecutor(this), object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                val bitmap = imageProxyToBitmap(image)
                showCaptureBitmap(bitmap)

                super.onCaptureSuccess(image)
            }

            override fun onError(exception: ImageCaptureException) {
                super.onError(exception)
            }
        })
    }

    private fun imageProxyToBitmap(image: ImageProxy): Bitmap {
        val planeProxy = image.planes[0]
        val buffer: ByteBuffer = planeProxy.buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder()
                .build()

            // Select back camera as a default
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture)

            } catch(exc: Exception) {
                Log.e("TEST", "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name))
                .apply {
                    mkdirs()
                }
        }

        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    private fun showCaptureBitmap(bitmap: Bitmap) : Boolean {
        if (ivResult.visibility == View.GONE) {
            ivResult.visibility = View.VISIBLE
            ivResult.setImageBitmap(bitmap)
            return false
        }

        return true
    }

    private fun hideCaptureBitmap() {
        ivResult.setImageBitmap(null)
        ivResult.visibility = View.GONE
    }

    override fun onBackPressed() {
        if (ivResult.visibility == View.VISIBLE) {
            Log.e("TEST", "CaptureImage true")
            hideCaptureBitmap()
        } else {
            onBackPressed()
            Log.e("TEST", "CaptureImage false")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.e("TEST", "권한 승인")
            startCamera()
        } else {
            Log.e("TEST", "권한 거부")
            onBackPressed()
        }
    }

    private fun permissionCheck() {
        val permissionList = listOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)

        if (!checkPermission(this, permissionList)) {
            requestPermission(this, permissionList)
        } else {
            startCamera()
        }
    }

    private fun checkPermission(context: Context, permissionList: List<String>): Boolean {
        for (i: Int in permissionList.indices) {
            if (ContextCompat.checkSelfPermission(context, permissionList[i]) == PackageManager.PERMISSION_DENIED) {
                return false
            }
        }
        return true
    }

    private fun requestPermission(activity: Activity, permissionList: List<String>){
        ActivityCompat.requestPermissions(activity, permissionList.toTypedArray(), 10)
    }
}