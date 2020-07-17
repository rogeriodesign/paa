package com.igti.paa

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela2.*

class Tela2Activity : AppCompatActivity() {
    private val receiver = BatteryReceiver()
    private var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)

        actionBar?.setDisplayHomeAsUpEnabled(true)

        intent.getStringExtra(EXTRANAME)?.let {
            name = it
        }

        textView4.text = name
    }

    override fun onSupportNavigateUp(): Boolean {
        finish();
        return true;
    }

    override fun onStart() {
        super.onStart()
        registerReceiver()
    }

    override fun onStop() {
        super.onStop()
        unRegisterReceiver()
    }

    private fun registerReceiver() {
        /* para registro estático escreva no manifest
        <receiver android:name=".BatteryReceiver" >
            <intent-filter>
                <action android:name="android.intent.action.ACTION_BATTERY_LOW" />
            </intent-filter>
        </receiver> */
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_LOW)
        registerReceiver(receiver, intentFilter)
    }

    private fun unRegisterReceiver() {
        unregisterReceiver(receiver)
    }

    companion object{
        val EXTRANAME = "extra_name"
    }
}
