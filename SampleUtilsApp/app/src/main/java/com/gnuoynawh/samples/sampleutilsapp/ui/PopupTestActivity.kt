package com.gnuoynawh.samples.sampleutilsapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.gnuoynawh.samples.sampleutilsapp.R
import com.gnuoynawh.samples.sampleutilsapp.popup.DatePickerDialog

class PopupTestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_popup)

        // 날짜 선택 팝업
        findViewById<AppCompatButton>(R.id.btn_date_picker).setOnClickListener {

            DatePickerDialog.Builder(this)
                .setOnDatePickerListener { year, monthOfYear, dayOfMonth ->
                    Toast.makeText(this, "선택된 날짜 : $year.$monthOfYear.$dayOfMonth", Toast.LENGTH_SHORT).show()
                }
                .build().show()
        }
    }
}