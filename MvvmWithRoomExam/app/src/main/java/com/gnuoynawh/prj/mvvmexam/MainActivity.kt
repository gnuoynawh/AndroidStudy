package com.gnuoynawh.prj.mvvmexam

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnuoynawh.prj.mvvmexam.databinding.ActivityMainBinding


/**
 * MVVM 예제
 *  - 참고
 *      https://jminie.tistory.com/168
 *      https://velog.io/@dldmswo1209/Android-MVVM-Pattern-%EC%A0%81%EC%9A%A9-%EC%98%88%EC%A0%9C
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
//    private lateinit var mainViewModel: MainViewModel
    private val adapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init ViewModel
        //mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        //binding.viewModel = mainViewModel
        //binding.lifecycleOwner

        // LiveData Observe!
        mainViewModel.items.observe(this) {
            adapter.setData(
                it.toList(),
                onItemClickListener = { audio ->
                    Toast.makeText(this@MainActivity, "click!! Title = ${audio.fileName}", Toast.LENGTH_SHORT).show()
                },
                onItemUpdateListener = { audio ->
                    mainViewModel.updateItem(audio)
                },
                onItemDeleteListener = { audio ->
                    mainViewModel.deleteItem(audio)
                }
            )
        }

        // init RecyclerView
        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        // Button Event
        binding.addButton.setOnClickListener {
            mainViewModel.addItem()
        }
    }
}