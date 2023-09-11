package com.gnuoynawh.prj.mvvmexam.presentation.main

import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnuoynawh.prj.mvvmexam.presentation.view.ListAdapter
import com.gnuoynawh.prj.mvvmexam.databinding.ActivityMainBinding
import com.gnuoynawh.prj.mvvmexam.presentation.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


/**
 * MVVM 예제
 */
internal class MainActivity: BaseActivity<MainViewModel>(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private val adapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init RecyclerView
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        // Button Event
        binding.addButton.setOnClickListener {
            viewModel.addItem()
        }

    }

    override fun observeData() {
        // LiveData Observe!
        viewModel.audioListLiveData.observe(this) {
            adapter.setData(
                it.toList(),
                onItemClickListener = { audio ->
                    Toast.makeText(this@MainActivity, "click!! Title = ${audio.fileName}", Toast.LENGTH_SHORT).show()
                },
                onItemUpdateListener = { audio ->
                    viewModel.updateItem(audio)
                },
                onItemDeleteListener = { audio ->
                    viewModel.deleteItem(audio)
                }
            )
        }
    }
}