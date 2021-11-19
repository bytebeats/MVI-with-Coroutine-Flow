package me.bytebeats.mvi.demo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.bytebeats.mvi.demo.intent.MainIntent
import me.bytebeats.mvi.demo.model.User
import me.bytebeats.mvi.demo.retrofit.RetrofitService
import me.bytebeats.mvi.demo.viewstate.MainState
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

    private val btn by lazy { findViewById<Button>(R.id.buttonFetchUser) }
    private val progressBar by lazy { findViewById<ProgressBar>(R.id.progressBar) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private val adapter by lazy { MainAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView.adapter = adapter
        lifecycleScope.launch {
            mainViewModel.state.collect { state ->
                when (state) {
                    is MainState.Idle -> {
                    }
                    is MainState.Loading -> {
                        btn.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                    is MainState.Users -> {
                        progressBar.visibility = View.GONE
                        btn.visibility = View.GONE
                        renderUserList(state.users)
                    }
                    is MainState.Error -> {
                        progressBar.visibility = View.GONE
                        btn.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, state.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        btn.setOnClickListener { sendFetchUsersIntent() }
    }

    private fun sendFetchUsersIntent() {
        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainIntent.FetchUser)
        }
    }

    private fun renderUserList(users: List<User>) {
        recyclerView.visibility = View.VISIBLE
        adapter.updateUsers(users)
    }
}