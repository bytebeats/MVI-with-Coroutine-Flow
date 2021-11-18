package me.bytebeats.mvi.demo.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import me.bytebeats.mvi.demo.intent.MainIntent
import me.bytebeats.mvi.demo.repository.MainRepository
import me.bytebeats.mvi.demo.state.MainState

/**
 * Created by bytebeats on 2021/11/18 : 19:47
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow<MainState>(MainState.Idle)

    val state: Flow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }

    private fun fetchUser() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Users(mainRepository.getUsers())
            } catch (exp: Exception) {
                MainState.Error(exp.message)
            }
        }
    }
}