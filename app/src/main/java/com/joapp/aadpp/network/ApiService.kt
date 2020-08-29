package com.joapp.aadpp.network

import com.joapp.aadpp.data.User
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface ApiService {
    @GET("hours")
    fun getHours(): Call<List<User>>

    @GET("skilliq")
    fun getSkilliq(): Call<List<User>>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}