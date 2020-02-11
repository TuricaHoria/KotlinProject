package com.example.myapplication.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Models.ToDo
import com.example.myapplication.Adapters.ToDoAdapter
import com.example.myapplication.Api.ClientRequestAPI
import com.example.myapplication.Models.Realm_ToDo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.exceptions.RealmException
import kotlinx.android.synthetic.main.to_do_fragment.*
import java.lang.RuntimeException

class ToDoFragment : Fragment() {


    private lateinit var fragmentActions: FragmentActions
    private var mCompositeDisposable: CompositeDisposable? = null
    private var mAdapter: ToDoAdapter? = null
    private lateinit var realm: Realm

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

        realm = Realm.getDefaultInstance()
        return inflater.inflate(R.layout.to_do_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getToDos()
        setupFavorites(view)
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

        if (context is FragmentActions) {
            fragmentActions = context
        } else {
            throw RuntimeException(context.toString() + "implement FragmentActions")
        }
    }

    private fun setUpRecyclerView(adapter: ToDoAdapter) {

        rv_to_do_list.adapter = adapter
        rv_to_do_list.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun getToDos() {

        val userId = arguments?.getInt(ARG_USERID) ?: return

        mCompositeDisposable = CompositeDisposable()

        mCompositeDisposable?.add(
            ClientRequestAPI.getToDos(userId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { toDos ->
                        mAdapter = ToDoAdapter(toDos) { toDoId ->
                            addToDoRealm(toDos)
                            val bundle = Bundle()
                            bundle.putInt(ARG_TODOID, toDoId)
                            fragmentActions.replaceFragment(bundle, NotificationSetupFragment.TAG)
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

    private fun setupFavorites(itemView: View) {

        val favoritesIcon = itemView.findViewById(R.id.iv_favorites_icon) as? ImageView
        var isFavoritesSelected = false

        favoritesIcon?.setOnClickListener {

            when (isFavoritesSelected) {

                false -> {
                    favoritesIcon.setImageResource(R.drawable.ic_selected_favorites)
                    isFavoritesSelected = true
                }

                true -> {
                    favoritesIcon.setImageResource(R.drawable.ic_unselected_favorites)
                    isFavoritesSelected = false
                }
            }
        }
    }

    fun addToDoRealm(toDos: MutableList<ToDo>) {

        realm.beginTransaction()
        try {
            for (toDo: ToDo in toDos) {
                var realmToDo = realm.createObject(Realm_ToDo::class.java, toDo.id)
                realmToDo.setId(toDo.id)
                realmToDo.setCompleted(toDo.completed)
                realmToDo.setTitle(toDo.title)
                realm.commitTransaction()
                Log.d(TAG, "Added ToDo list to Real")
            }

        } catch (e: RealmException) {

            Log.e(TAG, e.message)
        }
    }
}