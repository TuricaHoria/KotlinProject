package com.example.myapplication

import android.content.Context
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class UserFragment : Fragment() {

    private val TAG = MainActivity::class.java.simpleName

    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mUsers: MutableList<UserEntity>? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mCompositeDisposable = CompositeDisposable()

        mCompositeDisposable!!.add(
            ClientRequestAPI.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map {
                    mUsers = it }
                .subscribe()
        )

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

    private fun handleResponse (users : MutableList<UserEntity>)
    {
        mUsers = users
    }


}