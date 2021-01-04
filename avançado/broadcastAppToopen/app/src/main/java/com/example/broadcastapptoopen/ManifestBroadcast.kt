package com.example.broadcastapptoopen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ManifestBroadcast: BroadcastReceiver() {
    val TAG = "ManifestBroadcast"

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive chamado, ${intent?.action}")

        if (intent?.action == "MY_CUSTOM_ACTION_TO_OPEN_APP") {
            context?.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}