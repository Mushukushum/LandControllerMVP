package com.example.thirdparties.notification

import android.app.Activity
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast


class NotifyNotificationReceived:Activity(){

    private val action = "NOTIFICATION_RECEIVED"
    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = intent

        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")

        super.onCreate(savedInstanceState)

        val filter = IntentFilter(action)
        registerReceiver(broadcastReceiver, filter)

        displayAlert(title, body)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    private fun displayAlert(title:String?, body:String?) {

        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes"){_,_ ->
            Toast.makeText(this, intent.getStringExtra("key")+"\n"+intent.getStringExtra("value"), Toast.LENGTH_LONG).show()
            finish()
        }
        builder.setNegativeButton("No"){_,_ ->
            finish()
        }

        builder.setTitle(title)
        builder.setMessage(body)

        builder.create().show()
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
        }
    }
}
