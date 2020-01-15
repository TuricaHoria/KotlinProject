package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter (val toDos: ArrayList<ToDo>) : RecyclerView.Adapter<ToDoAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.to_do_fragment,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return toDos.size
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        val toDo : ToDo = toDos[position]

        holder.toDoTitleTV.text = toDo.title

        if(toDo.completed)
        holder.toDoCompletedTV.text = "Completed"
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val toDoTitleTV = itemView.findViewById(R.id.title) as TextView
        val toDoCompletedTV = itemView.findViewById(R.id.completed) as TextView
    }

}