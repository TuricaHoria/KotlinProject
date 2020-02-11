package com.example.myapplication.Models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Realm_ToDo : RealmObject() {

    @PrimaryKey
    private var id: Int = 0
    private lateinit var title: String
    private var completed: Boolean = false
    private var favorited: Boolean = false

    fun setId(id: Int) {
        this.id = id
    }

    fun getId(): Int {
        return id
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getTitle(): String {
        return title
    }

    fun setCompleted(completed: Boolean) {
        this.completed = completed
    }

    fun getCompleted(): Boolean {
        return completed
    }

    fun setFavorited(completed: Boolean) {
        this.favorited = favorited
    }

    fun getFavorited(): Boolean {
        return favorited
    }

}