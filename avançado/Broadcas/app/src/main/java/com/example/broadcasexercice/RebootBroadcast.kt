package com.example.broadcasexercice

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class RebootBroadcast: BroadcastReceiver() {

    val TAG = "RebootBroadcast"

    override fun onReceive(context: Context?, intent: Intent?) {

        Log.d(TAG, "onReceive chamado")

        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d(TAG, "sistema inicializado")
        }

        if (intent?.action == Intent.ACTION_LOCKED_BOOT_COMPLETED) {
            Log.d(TAG, "sistema inicializado com locked")
        }

        if (intent?.action == Intent.ACTION_REBOOT) {
            Log.d(TAG, "sistema reinicializado")
        }
    }

}