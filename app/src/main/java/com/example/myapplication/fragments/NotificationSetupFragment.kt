package com.example.myapplication.fragments

import android.app.*
import android.content.Context
import android.content.Intent
import java.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.myapplication.MyAlarmReceiver
import com.example.myapplication.R
import kotlinx.android.synthetic.main.to_do_options_fragment.*
import java.text.SimpleDateFormat
import java.time.Month

class NotificationSetupFragment : Fragment() {

    private val REQUEST_CODE = 100
    private var pendingIntent: PendingIntent? = null

    companion object {
        const val TAG = "NotificationSetup"
        fun newInstance(bundle: Bundle?): NotificationSetupFragment {
            val fragment = NotificationSetupFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.to_do_options_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val calendar = Calendar.getInstance()

        val alarmManager = getSystemService(context?:return , AlarmManager::class.java) as AlarmManager
        val intent = Intent(context, MyAlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        time_button.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, min ->

                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, min)
                TV_time.text = SimpleDateFormat("HH:mm").format(calendar.time)

            }

            TimePickerDialog(
                context,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()


        }


        date_button.setOnClickListener {

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            val pickerDialog = DatePickerDialog(
                context ?: return@setOnClickListener,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    TV_date.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)
                   /* calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)*/
                }, year, month, day
            )

            pickerDialog.show()
        }

        notification_button.setOnClickListener {

            var time = System.currentTimeMillis()
            Log.d(TAG , "Time in millis on the calendar is ${calendar.timeInMillis}")
            Log.d(TAG , "Current time in millis is $time")
            alarmManager.set(
                AlarmManager.RTC,
                calendar.timeInMillis,
                pendingIntent

            )
            Toast.makeText(context,"Created Alarm Notification" , Toast.LENGTH_SHORT).show()
        }
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)


    }


}