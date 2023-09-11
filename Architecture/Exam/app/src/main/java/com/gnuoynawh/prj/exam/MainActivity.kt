package com.gnuoynawh.prj.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnuoynawh.prj.exam.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter = ListAdapter()

    private var fileItem: MutableList<File> = mutableListOf()
    private val _fileList = MutableLiveData<MutableList<File>>()

    private val fileList: LiveData<MutableList<File>>
        get() = _fileList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // DataBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LiveData Observe!
        fileList.observe(this) {

            // 데이터 변경시 리스트에 데이터 업데이트
            adapter.setData(
                it.toList(),
                onItemClickListener = { file ->
                    Toast.makeText(this@MainActivity, "click!! ${file.fileName}", Toast.LENGTH_SHORT).show()
                }
            )
        }

        // init RecyclerView
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        // Button Event
        binding.addButton.setOnClickListener {
            lifecycleScope.launch {
                val index = fileItem.size + 1
                fileItem.add(File(index.toLong(), "Title $index"))
                _fileList.value = fileItem
            }
        }
    }
}