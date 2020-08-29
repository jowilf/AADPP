package com.joapp.aadpp.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface FormService {
    @POST("formResponse")
    @FormUrlEncoded
    fun submit(
        @Field("entry.1824927963")
        email: String,
        @Field("entry.1877115667")
        name: String,
        @Field("entry.1877115667")
        last_name: String,
        @Field("entry.284483984")
        link: String,
    ): Call<Void>

    companion object {
        fun create(): FormService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(FormService::class.java)
        }
    }
}