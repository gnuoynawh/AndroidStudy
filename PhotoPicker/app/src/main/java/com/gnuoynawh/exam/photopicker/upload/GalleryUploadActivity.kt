package com.gnuoynawh.exam.photopicker.upload

import android.Manifest.permission
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.bumptech.glide.Glide
import com.gnuoynawh.exam.photopicker.GallerySelectedAdapter
import com.gnuoynawh.exam.photopicker.Photo
import com.gnuoynawh.exam.photopicker.R

class GalleryUploadActivity : AppCompatActivity() {

    private val imageView by lazy {
        findViewById<AppCompatImageView>(R.id.imageview)
    }
    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }

    /**
     * 커스텀 갤러리
     * https://3001ssw.tistory.com/195
     *
     * 웹뷰 파일 업로드
     * https://minasb.tistory.com/28
     * https://gogorchg.tistory.com/422
     * https://cofs.tistory.com/182
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        checkPermissions(permission.READ_EXTERNAL_STORAGE)

    }
    private fun initView() {

        val fileList = ArrayList<Photo>()
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val projection = arrayOf(
            MediaStore.MediaColumns.DATA,
            MediaStore.MediaColumns.DISPLAY_NAME
        )

        val sortOrder = MediaStore.MediaColumns.DATE_ADDED + " desc"
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, sortOrder)

        if (cursor != null) {

            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            val columnDisplayName = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
            var lastIndex: Int? = null

            while (cursor.moveToNext()) {
                val absolutePathOfImage = cursor.getString(columnIndex)
                val nameOfFile = cursor.getString(columnDisplayName)

                lastIndex = absolutePathOfImage.lastIndexOf(nameOfFile)
                if (lastIndex < 0) {
                    lastIndex = nameOfFile.length - 1
                }

                if (!TextUtils.isEmpty(absolutePathOfImage)) {
                    fileList.add(Photo(absolutePathOfImage, false))
                }
            }
        }

        val adapter = GallerySelectedAdapter(this, fileList)
        adapter.setOnItemClickListener(object : GallerySelectedAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                Glide.with(this@GalleryUploadActivity)
                    .load(fileList[position].url)
                    .into(imageView)
            }
        })
        adapter.setOnCheckedChangeListener(object : GallerySelectedAdapter.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean, position: Int) {
                val count: Int = adapter.getCheckedCount()

                if (count > 5) {
                    Toast.makeText(this@GalleryUploadActivity, "5개까지 선택가능합니다", Toast.LENGTH_SHORT).show()
                    adapter.setChecked(false, position)
                }
            }
        })

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 4, GridLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(object: ItemDecoration() {
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

                val spacing: Int = dpToPx(this@GalleryUploadActivity, 3.0f)

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
                return TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    dp,
                    context.resources.displayMetrics
                ).toInt()
            }
        })

        recyclerView.smoothScrollToPosition(0)
    }

    private val REQUEST_CODE = 101

    private fun checkPermissions(permission: String) {
        val permissionCheck = ContextCompat.checkSelfPermission(this, permission)
        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            if (shouldShowRequestPermissionRationale(permission)) {
                //권한 설명 필요
            } else {
                requestPermissions(arrayOf<String>(permission), REQUEST_CODE)
            }
        } else {
            initView()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //권한 승인
                    initView()
                }  else {
                    //권한 거부
                    return
                }
            }
            else -> {
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}