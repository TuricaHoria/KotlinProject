package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Fragments.FragmentListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.to_do_fragment.*
import kotlinx.android.synthetic.main.users_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import java.lang.StringBuilder

class ToDoFragment : Fragment() {

    private val TAG = ToDoFragment::class.java.simpleName

    private lateinit var fragmentListener: FragmentListener

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mAdapter: ToDoAdapter? = null

    private var toDosList: MutableList<ToDo>? = null

    companion object {

        const val ARG_USERID = "userId"
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

        return inflater.inflate(R.layout.to_do_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       setUpAdapter()
        getToDos()
    }

    override fun onStart() {
        mCompositeDisposable = CompositeDisposable()
        super.onStart()
    }

    override fun onStop() {
        mCompositeDisposable?.dispose()
        super.onStop()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is FragmentListener) {
            fragmentListener = context
        } else {
            throw RuntimeException(context.toString() + "implement FragmentListener")
        }

    }

    fun setUpAdapter() {

        mAdapter = toDosList?.let { ToDoAdapter(it) }

    }

    fun setUpRecyclerView(adapter: ToDoAdapter) {

        to_do_recyclerview.adapter = adapter
        to_do_recyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    fun getToDos() {

        val userId = arguments?.getInt(ARG_USERID) ?: return

        mCompositeDisposable?.add(
            ClientRequestAPI.getToDos(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { toDos ->

                        mAdapter = ToDoAdapter(toDos)
                        mAdapter?.let { setUpRecyclerView(it) }

                    },
                    { ERROR ->

                        Log.e(TAG, "Error")
                    }
                )
        )

    }
}