package com.gnuoynawh.practice.mvi.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gnuoynawh.practice.mvi.CounterIntent
import com.gnuoynawh.practice.mvi.presentation.viewmodel.CounterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: CounterViewModel by viewModels()

//    private val viewModel : CounterViewModel by viewModels {
//        CounterViewModelFactory(CounterRepository(CounterDatabase.getDatabase(this).counterDao()))
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsState()
            Counter(viewModel, state.count)
        }
    }
}

@Composable
fun Counter(
    viewModel: CounterViewModel,
    count: Int
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Counter : $count",
            fontSize = 24.0.sp
        )
        Spacer(modifier = Modifier.height(16.0.dp))
        Row {
            Button(
                onClick = { viewModel.processIntent(CounterIntent.Increment) }
            ) {
                Text("증가")
            }
            Spacer(modifier = Modifier.width(8.0.dp))
            Button(
                onClick = { viewModel.processIntent(CounterIntent.Decrement) }
            ) {
                Text("감소")
            }
            Spacer(modifier = Modifier.width(8.0.dp))
            Button(
                onClick = { viewModel.processIntent(CounterIntent.Reset) }
            ) {
                Text("초기화")
            }
        }
    }
}