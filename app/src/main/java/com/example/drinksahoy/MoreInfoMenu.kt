package com.example.drinksahoy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MoreInfoMenu : AppCompatActivity() {

    var currentBeer = Beer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_info_menu)

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("beerInfo",currentBeer as Serializable)
            startActivity(intent)
        }
    }
}