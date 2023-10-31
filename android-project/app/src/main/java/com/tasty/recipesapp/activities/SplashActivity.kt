package com.tasty.recipesapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tasty.recipesapp.R


class SplashActivity : AppCompatActivity() {

    companion object{
        const val TAG = "SplashActivity"
    }

    // A splash idotartama (ms)
    private val SPLASH_TIME_OUT = 3000.00
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Use a HandlerThread to create a background thread
        val handlerThread = HandlerThread("SplashHandlerThread", -10)
        handlerThread.start() // Create a Handler on the new HandlerThread
        val handler = Handler(handlerThread.looper)
        handler.postDelayed({
            // Navigate to MainActivity after the delay
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() }, SPLASH_TIME_OUT.toLong())


    }

    override fun onStart(){
        super.onStart();
        Log.d(TAG, "onStart: SplashActivity started.")
    }

    override fun onResume(){
        super.onResume();
        Log.d(TAG, "onResume: SplashActivity resumed.")
    }

    override fun onPause(){
        super.onPause();
        Log.d(TAG, "onPause: SplashActivity paused.")
    }

    override fun onStop(){
        super.onStop();
        Log.d(TAG, "onStop: SplashActivity stoped.")
    }

    override fun onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy: SplashActivity destroyed.")
    }



}