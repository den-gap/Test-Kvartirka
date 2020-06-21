package com.example.testkvartira.api

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KvartirkaApiFactory {
    private const val BASE_URL = "http://api.beta.kvartirka.pro/client/1.4/"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()
    val apiService: KvartirkaApiService = retrofit.create(KvartirkaApiService::class.java)
}