package com.sucirahmawati.androidpraktikum8.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sucirahmawati.androidpraktikum8.model.User
import com.sucirahmawati.androidpraktikum8.network.ApiService
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    val response = MutableLiveData<User>()

    fun getUsers() {
        viewModelScope.launch {
            try {
                val listResult = ApiService.Api.retrofitService.getUsers()
                response.value = listResult
            } catch (e: Exception) {
                response.value = null
            }
        }
    }
}