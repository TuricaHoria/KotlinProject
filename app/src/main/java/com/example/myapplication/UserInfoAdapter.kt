package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserInfoAdapter (val userList : ArrayList<User>) : RecyclerView.Adapter<UserInfoAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.activity_main,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
            val user : User = userList[position]

            holder.textViewName.text = user.name
            holder.textViewUsername.text = user.username
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textViewName = itemView.findViewById(R.id.user_name) as TextView
        val textViewUsername = itemView.findViewById(R.id.user_username) as TextView
    }

}