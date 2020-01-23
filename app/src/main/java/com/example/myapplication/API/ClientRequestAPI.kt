package com.example.myapplication

import android.content.ContentValues.TAG
import android.util.Log
import io.reactivex.Observable

object ClientRequestAPI {

    val service = RetrofitClientInstance.retrofitInstance.create(APIServices::class.java)


    fun getUsers(): Observable<MutableList<UserEntity>> {

        Log.d(TAG , "Getting all users")
        return service.getAllUsers()

    }

    fun getToDos(userId : Int): Observable<MutableList<ToDo>> {
        return service.getToDos(userId)

    }
}