package com.gnuoynawh.prj.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnuoynawh.prj.exam.databinding.ActivityMainBinding

/**
 * MVVM 예제
 *  - 참고
 *      https://jminie.tistory.com/168
 *      https://velog.io/@dldmswo1209/Android-MVVM-Pattern-%EC%A0%81%EC%9A%A9-%EC%98%88%EC%A0%9C
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel = MainViewModel()
    private val adapter = ListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // init ViewModel
        binding.viewModel = mainViewModel
        binding.lifecycleOwner

        // LiveData Observe!
        mainViewModel.fileList.observe(this) {

            // 데이터 변경시 리스트에 데이터 업데이트
            adapter.setData(
                it.toList(),
                onItemClickListener = {
                    Toast.makeText(this@MainActivity, "click!! Title = ${it.fileName}", Toast.LENGTH_SHORT).show()
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