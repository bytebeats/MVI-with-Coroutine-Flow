package me.bytebeats.mvi.demo.retrofit

import me.bytebeats.mvi.demo.service.UserService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by bytebeats on 2021/11/18 : 19:05
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
object RetrofitService {
    private const val BASE_URL = "https://5e510330f2c0d300147c034c.mockapi.io/"
    private fun getRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun <T> getService(baseUrl: String, t: Class<T>): T = getRetrofit(baseUrl).create(t)

    val userApiService: UserService = getService(BASE_URL, UserService::class.java)
}