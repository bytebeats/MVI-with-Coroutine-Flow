package me.bytebeats.mvi.demo.intent

/**
 * Created by bytebeats on 2021/11/18 : 19:44
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
sealed class MainIntent {
    object FetchUser : MainIntent()
}
