package com.shamanth.twsearchclient.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.shamanth.twsearchclient.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseService {

    private val gson: Gson = GsonBuilder().create()
    private val httpClient = OkHttpClient().newBuilder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .readTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    //Currently not used
//    val imageRetrofit: Retrofit = Retrofit.Builder()
//        .baseUrl("https://twitter.com/")
//        .client(httpClient)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
}