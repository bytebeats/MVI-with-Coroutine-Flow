package me.bytebeats.mvi.demo.vm.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.bytebeats.mvi.demo.repository.MainRepository
import me.bytebeats.mvi.demo.usercase.ApiUserCase
import me.bytebeats.mvi.demo.vm.MainViewModel

/**
 * Created by bytebeats on 2021/11/18 : 20:13
 * E-mail: happychinapc@gmail.com
 * Quote: Peasant. Educated. Worker
 */
class MainViewModelFactory(private val apiUserCase: ApiUserCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiUserCase)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}