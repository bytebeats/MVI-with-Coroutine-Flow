package me.bytebeats.mvi.demo.usercase

import me.bytebeats.mvi.demo.model.User
import me.bytebeats.mvi.demo.service.UserService

/**
 * Created by bytebeats on 2021/11/18 : 19:03
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class ApiUserCaseImpl(private val userApiService: UserService) : ApiUserCase {
    override suspend fun getUsers(): List<User> = userApiService.getUsers()
}