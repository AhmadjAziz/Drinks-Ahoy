package com.example.drinksahoy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.io.Serializable

class MoreInfoMenu : AppCompatActivity() {

    var currentBeer = Beer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_info_menu)

        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("beerInfo", currentBeer as Serializable)
            startActivity(intent)
        }

        currentBeer = intent.extras!!.get("beerInfo") as Beer
        fullBearInfo()

    }
    private fun fullBearInfo(){

        //Display beer image using Picasso.
        val imgView = findViewById<ImageView>(R.id.imageView)
        Picasso
            .get()
            .load(currentBeer.imageUrl)
            .into(imgView)

        //Display beer name
        val nameView = findViewById<TextView>(R.id.name)
        nameView.text = "Name: ${currentBeer.name}"

        //Display the strength of beer
        //TODO Make sure beer displays strong icon when above 5% abv
        val strengthView = findViewById<TextView>(R.id.strength)
        strengthView.text = "Strength: ${currentBeer.strength}"

        //Display the tagline of beer.
        val taglineView = findViewById<TextView>(R.id.tagline)
        taglineView.text = "Tagline: ${currentBeer.tagline}"

        //Display the description of beer
        val descriptionView = findViewById<TextView>(R.id.description)
        descriptionView.text = "Description: ${currentBeer.description}"

        //Display the foods that pair with beer
        val foodPairView = findViewById<TextView>(R.id.food_pair)
        foodPairView.text = "Food pairing: ${currentBeer.foodPair}"

    }
}
