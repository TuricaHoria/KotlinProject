package com.example.myapplication.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Api.ClientRequestAPI
import com.example.myapplication.Adapters.UserInfoAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.users_fragment.*
import java.lang.RuntimeException


class UserFragment : Fragment() {

    private var fragmentActions: FragmentActions? = null
    private var mCompositeDisposable: CompositeDisposable? = null
    private var mAdapter: UserInfoAdapter? = null

    companion object {
        const val ARG_USERID = "userId"
        const val TAG = "UserFragment"
        fun newInstance(bundle: Bundle?): UserFragment {
            val fragment = UserFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.users_fragment, container, false)
    }

    override fun onStart() {
        mCompositeDisposable = CompositeDisposable()
        super.onStart()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rv_users.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        Log.d(TAG, "The view was created")

        mCompositeDisposable = CompositeDisposable()
        getUsers()

    }

    override fun onStop() {
        mCompositeDisposable?.dispose()
        super.onStop()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is FragmentActions) {
            fragmentActions = context
        } else {
            throw RuntimeException(context.toString() + "implement FragmentActions")
        }
    }

    fun getUsers() {

        mCompositeDisposable?.add(
            ClientRequestAPI.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { users ->
                        Log.d(TAG, "The users list is $users")

                        mAdapter = UserInfoAdapter(users) { userId ->
                            val bundle = Bundle()
                            bundle.putInt(ARG_USERID, userId)
                            Log.d(TAG, "The bundle sent is $bundle")
                            fragmentActions?.replaceFragment(bundle, ToDoFragment.TAG)
                        }
                        rv_users.adapter = mAdapter
                    },
                    { ERROR ->

                        Log.e(TAG, "Error", ERROR)
                    }
                )
        )

    }

}