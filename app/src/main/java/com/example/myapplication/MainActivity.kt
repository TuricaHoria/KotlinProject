package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.fragments.NotificationSetupFragment


class MainActivity : AppCompatActivity(), FragmentListener {

    private val TAG = MainActivity::class.java.simpleName

    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(null ,TAG = UserFragment::class.java.simpleName)
    }

    override fun replaceFragment(bundle: Bundle?, TAG: String) {
        val transaction = manager.beginTransaction()

        val fragment = when (TAG) {
            UserFragment::class.java.simpleName ->
                UserFragment.newInstance(bundle)

            ToDoFragment::class.java.simpleName ->
                ToDoFragment.newInstance(bundle)

            NotificationSetupFragment::class.java.simpleName ->
                NotificationSetupFragment.newInstance(bundle)

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

            NotificationSetupFragment::class.java.simpleName ->
                NotificationSetupFragment.newInstance(bundle)

            else -> return
        }

        transaction.add(R.id.frame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun removeFragment(TAG: String) {

    }

}
