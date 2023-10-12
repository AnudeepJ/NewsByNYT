package com.anudeep.nyt.app.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.anudeep.nyt.app.MainActivity
import com.anudeep.nyt.app.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            // Delay for 2 seconds (2000 milliseconds)
            delay(2000)
            // Start your main activity
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()

        }
        // Close the splash activity
    }
}