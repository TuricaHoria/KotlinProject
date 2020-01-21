package com.example.myapplication

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET


interface APIServices {

    @GET("users")
    fun getAllUsers() : Observable<MutableList<UserEntity>>

    @GET("todos")
    fun getToDos() : Observable<MutableList<ToDo>>
}