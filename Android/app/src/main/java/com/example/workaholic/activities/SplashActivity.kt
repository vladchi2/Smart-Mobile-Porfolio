package com.example.workaholic.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import com.example.workaholic.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        @Suppress("Deprecation")
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        @Suppress("Deprecation")
        Handler().postDelayed(
            {
                startActivity(Intent(this@SplashActivity,
                    LoginActivity::class.java))
                finish()
            }, 1500
        )
    }
}