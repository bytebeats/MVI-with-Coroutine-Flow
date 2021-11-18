package me.bytebeats.mvi.demo.repository

import me.bytebeats.mvi.demo.usercase.ApiUserCase

/**
 * Created by bytebeats on 2021/11/18 : 19:43
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class MainRepository(private val userCase: ApiUserCase) {
    suspend fun getUsers() = userCase.getUsers()
}