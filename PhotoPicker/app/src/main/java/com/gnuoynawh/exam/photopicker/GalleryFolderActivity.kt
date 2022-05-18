package com.gnuoynawh.exam.photopicker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.File


class GalleryFolderActivity: AppCompatActivity() {

    private val listView by lazy {
        findViewById<ListView>(R.id.listView)
    }

    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }

    private val images: ArrayList<String> = ArrayList()
    private var adapter: GalleryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_folder)

        getData()
        initGallery()
    }

    private fun getData() {

        val list = getImageFolders(this)

        val listAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list)
        listView.adapter = listAdapter
        listView.setOnItemClickListener { _, _, position, _ ->

            getImagesByBucket(this, list[position], images)
            adapter?.notifyDataChange(images)

        }
    }

    private fun initGallery() {

        adapter = GalleryAdapter(this, images)
        adapter!!.setOnItemClickListener(object : GalleryAdapter.OnItemClickListener {
            override fun onClick(position: Int) {

            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(object: RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)

                val position = parent.getChildAdapterPosition(view)
                val totalSpanCount = getTotalSpanCount(parent)
                val spanSize = getItemSpanSize(parent, position)

                if (totalSpanCount == spanSize) {
                    return
                }

                val spacing: Int = dpToPx(this@GalleryFolderActivity, 3.0f)

                outRect.top = if (isInTheFirstRow(position, totalSpanCount)) 0 else spacing
                outRect.left = if (isFirstInRow(position, totalSpanCount)) 0 else spacing / 2
                outRect.right = if (isLastInRow(position, totalSpanCount)) 0 else spacing / 2
                outRect.bottom = 0
            }

            private fun isInTheFirstRow(position: Int, spanCount: Int): Boolean {
                return position < spanCount
            }

            private fun isFirstInRow(position: Int, spanCount: Int): Boolean {
                return position % spanCount == 0
            }

            private fun isLastInRow(position: Int, spanCount: Int): Boolean {
                return isFirstInRow(position + 1, spanCount)
            }

            private fun getTotalSpanCount(parent: RecyclerView): Int {
                val layoutManager = parent.layoutManager
                return if (layoutManager is GridLayoutManager) layoutManager.spanCount else 1
            }

            private fun getItemSpanSize(parent: RecyclerView, position: Int): Int {
                val layoutManager = parent.layoutManager
                return if (layoutManager is GridLayoutManager) layoutManager.spanSizeLookup.getSpanSize(
                    position
                ) else 1
            }

            private fun dpToPx(context: Context, dp: Float): Int {
                return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
            }
        })
    }

    @SuppressLint("Range")
    fun getImageBuckets(context: Context): ArrayList<Bucket> {

        val buckets = ArrayList<Bucket>()

        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.DATA)

        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            var file: File
            while (cursor.moveToNext()) {
                val bucketPath = cursor.getString(cursor.getColumnIndex(projection[0]))
                val firstImage = cursor.getString(cursor.getColumnIndex(projection[1]))

                file = File(firstImage)
                if (file.exists()) {
                    buckets.add(Bucket(bucketPath, firstImage))
                }
            }
            cursor.close()
        }
        return buckets
    }


    @SuppressLint("Range")
    fun getImageFolders(context: Context): ArrayList<String> {

        val buckets = ArrayList<String>()

        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.Media.DATA)

        val cursor = context.contentResolver.query(uri, projection, null, null, null)
        if (cursor != null) {
            var file: File
            while (cursor.moveToNext()) {
                val bucketPath = cursor.getString(cursor.getColumnIndex(projection[0]))
                val firstImage = cursor.getString(cursor.getColumnIndex(projection[1]))

                Log.e("TEST", "[ getImageFolders ] path : $bucketPath")
                file = File(firstImage)
                if (file.exists() && !buckets.contains(bucketPath)) {
                    buckets.add(bucketPath)
                }
            }
            cursor.close()
        }
        return buckets
    }

    @SuppressLint("Range")
    fun getImagesByBucket(context: Context, bucketPath: String): ArrayList<String> {

        val images = ArrayList<String>()

        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val selection = MediaStore.Images.Media.BUCKET_DISPLAY_NAME + "=?"
        val orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC"

        val cursor = context.contentResolver.query(uri, projection, selection, arrayOf(bucketPath), orderBy)
        if (cursor != null) {
            var file: File
            while (cursor.moveToNext()) {
                val path = cursor.getString(cursor.getColumnIndex(projection[0]))

                file = File(path)
                if (file.exists() && !images.contains(path)) {
                    images.add(path)
                }
            }
            cursor.close()
        }
        return images
    }

    @SuppressLint("Range")
    fun getImagesByBucket(context: Context, bucketPath: String, list: ArrayList<String>) {

        list.clear()

        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val selection = MediaStore.Images.Media.BUCKET_DISPLAY_NAME + "=?"
        val orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC"

        val cursor = context.contentResolver.query(uri, projection, selection, arrayOf(bucketPath), orderBy)
        if (cursor != null) {
            var file: File
            while (cursor.moveToNext()) {
                val path = cursor.getString(cursor.getColumnIndex(projection[0]))

                Log.e("TEST", "[ getImagesByBucket ] path : $path")
                file = File(path)
                if (file.exists() && !list.contains(path)) {
                    list.add(path)
                }
            }
            cursor.close()
        }
    }
}