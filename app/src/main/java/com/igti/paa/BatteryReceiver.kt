package com.igti.paa

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BatteryReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context?.applicationContext, "Atenção!!! Bateria Fraca.", Toast.LENGTH_SHORT).show();
    }
}