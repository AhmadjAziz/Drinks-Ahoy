package com.example.drinksahoy

import android.media.Image
import android.nfc.tech.TagTechnology
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.google.android.material.card.MaterialCardView
import com.google.gson.JsonArray
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        //displays a random beer onCreate when app is launched
        callAPI()


        //button press connects android to punk api to fetch a random beer data
        val nextBeerBtn = findViewById<Button>(R.id.button)
        nextBeerBtn.setOnClickListener {
            callAPI()
            }
        }

    private fun callAPI(){
        Ion.with(this)
            .load("https://api.punkapi.com/v2/beers/random")
            .asString()
            .setCallback { e, result -> processBeer(result) }
    }
    //Passes data into methods where they can be extracted
    private fun processBeer(beerData: String) {

        val beerJson = JSONArray(beerData)
        processImage(beerJson)
        processName(beerJson)
        processStrength(beerJson)
//        processTagline(beerJson)
//        processDesc(beerJson)
//        processPair(beerJson)
    }

    //processes strength data and extracts it into a string format.
    private fun processStrength(beerJson: JSONArray) {
        val strengthData = beerJson
            .getJSONObject(0)
            .getString("abv")
        val nameView = findViewById<TextView>(R.id.strength)
        nameView.text = "Strength: " + strengthData
    }

    //extracts the name of the beer.
    private fun processName(beerJson: JSONArray) {

        val nameData = beerJson
            .getJSONObject(0)
            .getString("name")
        val nameView = findViewById<TextView>(R.id.name)
        nameView.text = "Name: " + nameData
    }

    //extracts image url from the Json array and inserts it into the image view.
    private fun processImage(beerJson: JSONArray) {

        val image_url = beerJson
            .getJSONObject(0)
            .getString("image_url")
        val imgView = findViewById<ImageView>(R.id.imageView)
        Picasso
            .get()
            .load(image_url)
            .into(imgView)
    }
}


