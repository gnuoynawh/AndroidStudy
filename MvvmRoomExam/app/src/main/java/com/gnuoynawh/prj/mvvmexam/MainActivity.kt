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
 *      https://todaycode.tistory.com/34
 *      https://underdog11.tistory.com/entry/Kotlin-MVVM-Room-Database-%EC%82%AC%EC%9A%A9%EB%B2%95-%EB%B0%8F-%EC%82%AC%EC%9A%A9%EC%98%88%EC%A0%9C-Entity-RoomDatabase-DAO-repository-ViewModel-coroutine-MVVM-%EA%B5%AC%EC%84%B1%ED%95%98%EA%B8%B0
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val adapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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