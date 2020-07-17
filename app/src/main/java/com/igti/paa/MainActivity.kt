package com.igti.paa

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.igti.paa.Tela2Activity.Companion.EXTRANAME
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val receiver = BatteryReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listeners()
        checksFirstAccess()
    }

    private fun checksFirstAccess() {
        val sharedPreferences =
            getSharedPreferences(OPENKEY, MODE_PRIVATE)

        if (sharedPreferences.getString(OPENKEY, "") != RESPONSEKEY) {
            val editor = sharedPreferences.edit()
            editor.putString(OPENKEY, RESPONSEKEY)
            editor.apply()

            val intent = Intent(this, SplashScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun listeners() {
        button.setOnClickListener {
            val intent = Intent(this, Tela2Activity::class.java)
            intent.putExtra(EXTRANAME, "Rogério Machado Porto")
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        registerReceiver()
        Log.i("ciclodevida", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclodevida", "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclodevida", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclodevida", "onPause")
    }

    override fun onStop() {
        super.onStop()
        unRegisterReceiver()
        Log.i("ciclodevida", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclodevida", "onDestroy")
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
        val OPENKEY = "open_key"
        val RESPONSEKEY = "second_time"
    }

}
