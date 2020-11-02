package com.shamanth.twsearchclient.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseService {

    val gson = GsonBuilder().create()
    private val httpClient = OkHttpClient().newBuilder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://6f8a2fec-1605-4dc7-a081-a8521fad389a.mock.pstmn.io")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val imageretrofit = Retrofit.Builder()
        .baseUrl("https://twitter.com/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}