package me.bytebeats.mvi.demo.service

import me.bytebeats.mvi.demo.model.User
import retrofit2.http.GET

/**
 * Created by bytebeats on 2021/11/18 : 19:04
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
interface UserService {
    @GET("users")
    suspend fun getUsers(): List<User>
}