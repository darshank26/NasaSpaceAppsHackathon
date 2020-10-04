package com.example.nasaspaceappshackathon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main_screen.*

class MainScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main_screen)

        frp_data.setOnClickListener {
            val intent = Intent(this@MainScreen,MainActivity::class.java)
            startActivity(intent)
        }

        map_data.setOnClickListener {
            val intent = Intent(this@MainScreen,MapsActivity2::class.java)
            startActivity(intent)
        }

        fab_more.setOnClickListener {
            val intent = Intent(this@MainScreen,MoreActivity::class.java)
            startActivity(intent)
        }    }
}
