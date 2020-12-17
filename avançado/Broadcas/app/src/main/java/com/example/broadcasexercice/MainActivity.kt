package com.example.broadcasexercice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val receiver: BroadcastReceiver = InternalBroadcast()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonSDKLass26.setOnClickListener {
            val intent = Intent(this, ManifestBroadcast::class.java).also {
                it.action = "MY_CUSTOM_ACTION_ON_MANIFEST"
                sendBroadcast(it)

            }
        }

        buttonSDKGreaterOrEqual26.setOnClickListener {
            val intent = Intent("MY_CUSTOM_ACTION_ON_INTERNEAL_BROADCAST").also {
                LocalBroadcastManager.getInstance(this).sendBroadcast(it)
            }
        }

        buttonOpenApp.setOnClickListener {

        }
    }

    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, IntentFilter("MY_CUSTOM_ACTION_ON_INTERNEAL_BROADCAST"))

    }

    override fun onPause() {
        super.onPause()

        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver)
    }

    inner class InternalBroadcast: BroadcastReceiver() {

        val TAG = "MainActivity - InternalBroadcast"

        override fun onReceive(context: Context?, intent: Intent?) {

            Log.d(TAG, "onReceive chamado")

            if (intent?.action == "MY_CUSTOM_ACTION_ON_INTERNEAL_BROADCAST") {
                Log.d(TAG, "Chamada registrada em uma activity")
            }
        }
    }
}