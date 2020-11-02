package com.shamanth.twsearchclient

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.shamanth.twsearchclient.main_screen.ui.MainActivity
import com.shamanth.twsearchclient.utility.AppConstants

class SplashScreen: AppCompatActivity() {

    //splash screen we can add loading animations or if there is a initialization we can use this screeen to initiallize
    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen,
                MainActivity::class.java))
        },AppConstants.SPLASH_TIME)
    }
}