package me.bytebeats.mvi.demo.state

import me.bytebeats.mvi.demo.model.User

/**
 * Created by bytebeats on 2021/11/18 : 19:45
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
sealed class MainState {
    object Idle : MainState()
    object Loading : MainState()
    data class Users(val users: List<User>) : MainState()
    data class Error(val error: CharSequence?) : MainState()
}
