package com.example.drinksahoy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MoreInfoMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_info_menu)

//        val backBtn = findViewById<Button>(R.id.backBtn)
//        backBtn.setOnClickListener{
//            val intent = Intent(this,MainActivity::class.java)
//            intent.putExtra("beerInfo",getString(R.string.lessInfo))
//            startActivity(intent)
//        }

    }
}