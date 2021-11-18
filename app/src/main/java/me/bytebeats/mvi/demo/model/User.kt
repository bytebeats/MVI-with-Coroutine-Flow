package me.bytebeats.mvi.demo.model

import com.squareup.moshi.Json

/**
 * Created by bytebeats on 2021/11/18 : 18:58
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
data class User(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "email")
    val email: String = "",
    @Json(name = "avatar")
    val avatar: String = ""
)
