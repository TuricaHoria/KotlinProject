package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.lang.StringBuilder


class UserFragment : Fragment() {


    lateinit var compositeDisopasble: CompositeDisposable

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val service = RetrofitClientInstance.retrofitInstance?.create(GetUserService::class.java)

        val call: Call<MutableList<UserEntity>> = service!!.getAllUsers()

        call.enqueue(object : Callback<MutableList<UserEntity>> {

            override fun onFailure(call: Call<MutableList<UserEntity>>, t: Throwable) {
                Log.e("Error", t.message.toString())
            }

            override fun onResponse(
                call: Call<MutableList<UserEntity>>,
                response: Response<MutableList<UserEntity>>
            ) {
                val allUsers: MutableList<UserEntity> = response.body()!!

                val stringBuilder = StringBuilder()

                for (userEntity: UserEntity in allUsers) {

                    stringBuilder.append(userEntity.user.username)
                    stringBuilder.append("\n")
                    stringBuilder.append(userEntity.user.email)
                    stringBuilder.append("\n")
                    stringBuilder.append(userEntity.user.id)
                    stringBuilder.append("\n")
                    stringBuilder.append(userEntity.user.name)
                    stringBuilder.append("\n")
                    stringBuilder.append("\n")
                }
            }
        })
        return inflater.inflate(R.layout.users_fragment, container, false)

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