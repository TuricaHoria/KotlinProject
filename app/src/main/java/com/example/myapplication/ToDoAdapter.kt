package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter (val toDoList : ArrayList<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>()
{
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val toDo : ToDo = toDoList[position]
        holder.titleTextView.text = toDo.title
        holder.completedTextView.text = toDo.completed.toString()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.to_do_fragment,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val titleTextView = itemView.findViewById(R.id.title) as TextView
        val completedTextView = itemView.findViewById(R.id.completed) as TextView
    }

}