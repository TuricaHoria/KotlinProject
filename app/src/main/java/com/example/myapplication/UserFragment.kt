package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit




class UserFragment: Fragment(){
    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.users_fragment,container,false)


        RetrofitClientInstance.retrofitInstance?.create()

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

    fun jsonGetRequest(urlQueryString: String): String? {
        var json: String? = null
        try {
            val url = URL(urlQueryString)
            val connection = url.openConnection() as HttpURLConnection
            connection.setDoOutput(true)
            connection.setInstanceFollowRedirects(false)
            connection.setRequestMethod("GET")
            connection.setRequestProperty("Content-Type", "application/json")
            connection.setRequestProperty("charset", "utf-8")
            connection.connect()
            val inStream = connection.getInputStream()
            json = streamToString(inStream) // input stream to string
        } catch (ex: IOException) {
            ex.printStackTrace()
        }

        return json
    }

    fun streamToString(inputStream: InputStream): String {
        return Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next()
    }
}