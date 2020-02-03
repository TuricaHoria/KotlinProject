package com.example.myapplication.Adapters

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Models.ToDo

class ToDoAdapter(val toDoList: MutableList<ToDo>, val onToDoSelected: (Int) -> Unit) :
    RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val toDo: ToDo = toDoList[position]
        holder.bind(toDo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.to_do, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTextView = itemView.findViewById(R.id.tv_to_do_title) as TextView
        val completedTextView = itemView.findViewById(R.id.tv_to_do_completed) as TextView
        val idTextView = itemView.findViewById(R.id.tv_to_do_id) as TextView
        val cardViewToDo = itemView.findViewById(R.id.cv_to_do) as CardView

        fun bind(toDo: ToDo) {

            titleTextView.text = toDo.title
            completedTextView.text = toDo.completed.toString()
            idTextView.text = toDo.id.toString()
            cardViewToDo.setOnClickListener {
                onToDoSelected.invoke(toDo.id)
                Log.d(ContentValues.TAG, "Invoked the Todo with id ${toDo.id} ")
            }
        }
    }
}
