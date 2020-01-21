package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance  {


private var retrofit : Retrofit? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val retrofitInstance:Retrofit?

    get() {

        if(retrofit == null)
        {
            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    return retrofit
    }


}