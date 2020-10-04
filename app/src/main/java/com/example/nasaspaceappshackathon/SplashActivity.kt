package com.example.nasaspaceappshackathon

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.onesignal.OneSignal


class SplashActivity : AppCompatActivity() {

    var mImg: ImageView? = null
    val SPLASH_SCREEN_TIME_OUT = 2400

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash)
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()

        mImg = findViewById(R.id.splash_icon_img)


        startSplashScreen()

    }

    private fun startSplashScreen() {
//        startAnimation()

        Handler().postDelayed(
            {

                    startActivity(Intent(this@SplashActivity, MainScreen::class.java))
                    overridePendingTransition(R.anim.fade_in_anim, R.anim.fade_out_anim)
                    finish()

            }, SPLASH_SCREEN_TIME_OUT.toLong()
        )
    }

//    private fun startAnimation() {
//        val mAnimation =
//            AnimationUtils.loadAnimation(this, R.anim.splash_zoom)
//        mImg!!.startAnimation(mAnimation)
//    }


}
