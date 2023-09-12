package com.gnuoynawh.prj.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gnuoynawh.prj.exam.data.entity.File
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
        presenter = MainPresenter(application)
        presenter.items.observe(this) {
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
            },
            onItemUpdateListener = { file ->
                presenter.updateItem(file)
            },
            onItemDeleteListener = { file ->
                presenter.deleteItem(file)
            }
        )
    }

    override fun showToast(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }
}