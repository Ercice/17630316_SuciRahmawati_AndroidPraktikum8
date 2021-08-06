package com.sucirahmawati.androidpraktikum8.ui.jenisbarang

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sucirahmawati.androidpraktikum8.model.Jenisbarang
import com.sucirahmawati.androidpraktikum8.network.ApiService
import kotlinx.coroutines.launch
import okhttp3.Response

class JenisbarangViewModel : ViewModel() {
    val response = MutableLiveData<Jenisbarang>()
    val createResponse = MutableLiveData<Response<Jenisbarang.JenisbarangResponse>>()

    fun getJenisbarang() {
        viewModelScope.launch {
            try {
                val listResult = ApiService.Api.retrofitService.getJenisbarang()
                response.value = listResult
            } catch (e: Exception) {
                response.value = null
            }
        }
    }

    fun create(jenisbarangData: Jenisbarang.JenisbarangData) {
        viewModelScope.launch {
            val response = ApiService.Api.retrofitService.create(jenisbarangData)
            createResponse.value = response
        }
    }
}