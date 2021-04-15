package com.definitely.notinstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.plugins.RxJavaPlugins

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RxJavaPlugins.setErrorHandler {
            Log.d(TAG, "onCreate: ${it.cause}")
        }
    }
}