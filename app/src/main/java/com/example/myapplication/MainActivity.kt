package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.LinearLayout
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userFragment : UserFragment
        val toDoFragment : ToDoFragment

        val usersRecyclerView = findViewById(R.id.user_recyclerview) as RecyclerView
        val toDosRecyclerView = findViewById(R.id.to_do_recyclerview) as RecyclerView

        usersRecyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        toDosRecyclerView.layoutManager = LinearLayoutManager(this , RecyclerView.VERTICAL , false)



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


}
