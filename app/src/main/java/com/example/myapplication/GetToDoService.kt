package com.example.myapplication

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET


interface GetToDoService {

    @GET("todos")
    fun getToDos() : Observable<MutableList<ToDo>>
}