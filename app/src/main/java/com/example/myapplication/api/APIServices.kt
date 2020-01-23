package com.example.myapplication.api

import com.example.myapplication.models.ToDo
import com.example.myapplication.models.UserEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface APIServices {

    @GET("users")
    fun getAllUsers() : Observable<MutableList<UserEntity>>

    @GET("todos")
    fun getToDos(
        @Query ("userId") userId : Int
    ) : Observable<MutableList<ToDo>>
}