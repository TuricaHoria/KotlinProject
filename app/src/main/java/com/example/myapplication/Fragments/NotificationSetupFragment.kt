package com.example.myapplication.Fragments

import android.app.*
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
import com.example.myapplication.Receivers.MyAlarmReceiver
import com.example.myapplication.R
import kotlinx.android.synthetic.main.to_do_options_fragment.*
import java.text.SimpleDateFormat

class NotificationSetupFragment : Fragment() {

    private val REQUEST_CODE_PENDING_INTENT = 100
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
        val alarmManager =
            getSystemService(context ?: return, AlarmManager::class.java) as AlarmManager
        val intent = Intent(context, MyAlarmReceiver::class.java)
        pendingIntent = PendingIntent.getBroadcast(
            context,
            REQUEST_CODE_PENDING_INTENT,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        onClickTimeButton(calendar)
        onClickDateButton(calendar)
        onNotificationButtonSetAlarm(calendar, alarmManager)
    }

    fun onClickTimeButton(calendar: Calendar) {
        bt_time.setOnClickListener {

            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, min ->

                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, min)
                tv_time.text = SimpleDateFormat("HH:mm").format(calendar.time)
            }

            TimePickerDialog(
                context,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }

    fun onClickDateButton(calendar: Calendar) {
        bt_date.setOnClickListener {

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val pickerDialog = DatePickerDialog(
                context ?: return@setOnClickListener,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    tv_date.setText("" + dayOfMonth + "/" + monthOfYear + "/" + year)

                }, year, month, day
            )

            pickerDialog.show()
        }
    }

    fun onNotificationButtonSetAlarm(calendar: Calendar, alarmManager: AlarmManager) {
        bt_notification.setOnClickListener {

            val time = System.currentTimeMillis()
            Log.d(TAG, "Time in millis on the calendar is ${calendar.timeInMillis}")
            Log.d(TAG, "Current time in millis is $time")
            alarmManager.set(
                AlarmManager.RTC,
                calendar.timeInMillis,
                pendingIntent
            )
            Toast.makeText(context, "Created Alarm Notification", Toast.LENGTH_SHORT).show()
        }
    }
}