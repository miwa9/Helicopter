package com.example.helicopter.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.helicopter.R

class SplashScreamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_scream)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity:: class.java))
            finish()
        },1000)

    }
}