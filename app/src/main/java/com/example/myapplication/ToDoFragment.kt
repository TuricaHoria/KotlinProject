package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class ToDoFragment : Fragment() {
    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {



        val service = RetrofitClientInstance.retrofitInstance?.create(GetToDoService::class.java)

        val call: Call<MutableList<ToDo>> = service!!.getToDos()

        call.enqueue(object : Callback<MutableList<ToDo>> {

            override fun onFailure(call: Call<MutableList<ToDo>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(
                call: Call<MutableList<ToDo>>,
                response: Response<MutableList<ToDo>>
            ) {
                val toDos: MutableList<ToDo> = response.body()!!

                val stringBuilder = StringBuilder()

                for (toDo: ToDo in toDos) {

                    stringBuilder.append(toDo.title)
                    stringBuilder.append("\n")
                    stringBuilder.append(toDo.id)
                    stringBuilder.append("\n")
                    stringBuilder.append(toDo.completed)
                    stringBuilder.append("\n")
                    stringBuilder.append("\n")
                }
            }
        })

        return inflater.inflate(R.layout.to_do_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
    }
}