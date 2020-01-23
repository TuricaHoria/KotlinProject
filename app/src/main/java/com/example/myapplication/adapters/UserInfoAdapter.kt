package com.example.myapplication.adapters

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.UserEntity

class UserInfoAdapter(
    private val userList: MutableList<UserEntity>,
    val onUserSelected: (Int) -> Unit
) : RecyclerView.Adapter<UserInfoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.user_info, parent, false)

        Log.d(TAG, "The value of the view is $v")
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user: UserEntity = userList[position]

        holder.bind(user)

    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textViewName = itemView.findViewById(R.id.user_name) as TextView
        val textViewUsername = itemView.findViewById(R.id.user_username) as TextView
        val textViewId = itemView.findViewById(R.id.user_id) as TextView
        val textViewEmail = itemView.findViewById(R.id.user_email) as TextView
        val cardViewUser = itemView.findViewById(R.id.cv_user) as CardView

        fun bind(user: UserEntity) {

            textViewName.text = user.name
            Log.d(TAG, "The value of the user's name is ${user.name}")

            textViewUsername.text = user.username
            Log.d(TAG, "The value of the user's username is ${user.username}")

            textViewId.text = user.id.toString()
            Log.d(TAG, "The value of the user's id is ${user.id}")

            textViewEmail.text = user.email
            Log.d(TAG, "The value of the user's email is ${user.email}")

            cardViewUser.setOnClickListener {
                onUserSelected.invoke (user.id)
                Log.d(TAG, "Invoked the user id ")

            }
        }
    }


}