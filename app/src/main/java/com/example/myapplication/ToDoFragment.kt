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
import com.example.myapplication.models.ToDo
import com.example.myapplication.adapters.ToDoAdapter
import com.example.myapplication.api.ClientRequestAPI
import com.example.myapplication.fragments.NotificationSetupFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.to_do_fragment.*
import java.lang.RuntimeException

class ToDoFragment : Fragment() {


    private lateinit var fragmentListener: FragmentListener

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mAdapter: ToDoAdapter? = null

    private var toDosList: MutableList<ToDo>? = null

    companion object {
        const val TAG = "ToDoFragment"
        const val ARG_USERID = "userId"
        const val ARG_TODOID = "toDoId"
        fun newInstance(bundle: Bundle?): ToDoFragment {
            val fragment = ToDoFragment()
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
    fun setUpRecyclerView(adapter: ToDoAdapter) {

        to_do_recyclerview.adapter = adapter
        to_do_recyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    fun getToDos() {

        val userId = arguments?.getInt(ARG_USERID) ?: return

        mCompositeDisposable = CompositeDisposable()

        mCompositeDisposable?.add(
            ClientRequestAPI.getToDos(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { toDos ->
                        mAdapter = ToDoAdapter(toDos){toDoId ->
                            val bundle = Bundle()
                            bundle.putInt(ARG_TODOID,toDoId)
                            fragmentListener.replaceFragment(bundle, NotificationSetupFragment.TAG)

                        }
                        mAdapter?.let {
                            setUpRecyclerView(it)

                        }


                    },
                    { ERROR ->

                        Log.e(TAG, "Error")
                    }
                )
        )

    }
}