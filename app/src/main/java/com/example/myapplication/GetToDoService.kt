package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET


interface GetToDoService {

    @GET("todos")
    fun getToDos() : Call<MutableList<ToDo>>
}