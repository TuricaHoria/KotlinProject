package com.example.myapplication

import android.content.ContentValues.TAG
import android.util.Log
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {


    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val httpClient = OkHttpClient.Builder()

    val retrofitInstance: Retrofit
        get() {

            Log.d(TAG, "Created retrofit instance")


            return retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }


}