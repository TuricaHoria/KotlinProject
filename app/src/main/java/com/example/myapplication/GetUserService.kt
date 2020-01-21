package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET


interface GetUserService {

    @GET("users")
    fun getAllUsers() : Call<MutableList<UserEntity>>
}