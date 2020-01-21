package com.example.myapplication

import io.reactivex.Observable

object ClientRequestAPI {

    val service = RetrofitClientInstance.retrofitInstance?.create(APIServices::class.java)


    fun getUsers(): Observable<MutableList<UserEntity>>? {
        return service?.getAllUsers()

    }

    fun getToDos(): Observable<MutableList<ToDo>>? {
        return service?.getToDos()

    }
}