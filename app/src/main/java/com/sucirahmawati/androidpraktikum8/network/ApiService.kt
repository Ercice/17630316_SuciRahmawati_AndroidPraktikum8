package com.sucirahmawati.androidpraktikum8.network

import android.telecom.Call
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.sucirahmawati.androidpraktikum8.model.Jenisbarang
import com.sucirahmawati.androidpraktikum8.model.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    private const val BASE_URL = "http://127.0.0.1/praktikum-penjualan-api-starter/api/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()


    interface ApiService {
        @GET("users?page=1")
        suspend fun getUsers(): User

        @GET("jenisbarang/read.php")
        suspend fun getJenisbarang(): Jenisbarang
    }

    object Api {
        val retrofitService : ApiService by lazy {
            retrofit.create(ApiService::class.java) }
    }

    @POST("jenisbarang/create.php")
    suspend fun create(@Body jenisbarangData: Jenisbarang.JenisbarangData): Response<Jenisbarang.JenisbarangResponse>

    @POST("jenisbarang/delete.php")
    fun delete(@Body jenisbarangData: Jenisbarang.JenisbarangData): Call<Jenisbarang.JenisbarangResponse>
}