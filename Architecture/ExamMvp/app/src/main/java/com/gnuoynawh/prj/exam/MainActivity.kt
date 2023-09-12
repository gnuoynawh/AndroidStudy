package com.gnuoynawh.prj.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnuoynawh.prj.exam.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: MainPresenter
    private val adapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner

        //
        presenter = MainPresenter(this@MainActivity)
        presenter.fileList.observe(this) {
            updateList(it.toList())
        }

        //
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        //
        binding.addButton.setOnClickListener {
            presenter.addItem()
        }
    }

    override fun updateList(list: List<File>) {
        adapter.setData(
            list,
            onItemClickListener = { file ->
                showToast(file.fileName)
            }
        )
    }

    override fun showToast(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }


    /*
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

    }*/
}