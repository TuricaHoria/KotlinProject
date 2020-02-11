package com.example.myapplication.Models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Realm_UserEntity : RealmObject() {

    @PrimaryKey
    private var id: Int = 0
    private lateinit var email: String
    private lateinit var username: String
    private lateinit var name: String

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return name
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun getUsername(): String {
        return username
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail(): String {
        return email
    }

}