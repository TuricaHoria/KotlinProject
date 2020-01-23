package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Fragments.FragmentListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.users_fragment.*


class MainActivity : AppCompatActivity(), FragmentListener {

    private val TAG = MainActivity::class.java.simpleName

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(TAG = UserFragment::class.java.name)
    }

    override fun replaceFragment(bundle: Bundle?, TAG: String) {
        val transaction = manager.beginTransaction()

        val fragment = when (TAG) {
            UserFragment::class.java.name ->
                UserFragment.newInstance(bundle)

            ToDoFragment::class.java.name ->
                ToDoFragment.newInstance(bundle)

            else -> return
        }


        transaction.replace(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun addFragment(bundle: Bundle?, TAG: String) {
        val transaction = manager.beginTransaction()

        val fragment = when (TAG) {
            UserFragment::class.java.name ->
                UserFragment.newInstance(bundle)

            ToDoFragment::class.java.name ->
                ToDoFragment.newInstance(bundle)

            else -> return
        }

        transaction.add(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun removeFragment(TAG: String) {

    }

}
