package me.bytebeats.mvi.demo.usercase

import me.bytebeats.mvi.demo.model.User

/**
 * Created by bytebeats on 2021/11/18 : 19:02
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
interface ApiUserCase {
    suspend fun getUsers(): List<User>
}