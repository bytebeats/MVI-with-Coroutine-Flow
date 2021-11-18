package me.bytebeats.mvi.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.bytebeats.mvi.demo.intent.MainIntent
import me.bytebeats.mvi.demo.retrofit.RetrofitService
import me.bytebeats.mvi.demo.state.MainState
import me.bytebeats.mvi.demo.usercase.ApiUserCaseImpl
import me.bytebeats.mvi.demo.vm.MainViewModel
import me.bytebeats.mvi.demo.vm.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val mainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory(ApiUserCaseImpl(RetrofitService.userApiService))
        )[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            mainViewModel.state.collect { state ->
                when (state) {
                    is MainState.Idle -> {
                    }
                    is MainState.Loading -> {
                    }
                    is MainState.Users -> {
                    }
                    is MainState.Error -> {
                    }
                }
            }
        }
    }

    private fun sendFetchUsersIntent() {
        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainIntent.FetchUser)
        }
    }
}