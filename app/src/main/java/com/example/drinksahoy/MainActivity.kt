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

        //button press connects android to punk api to fetch a random beer data
        val nextBeerBtn = findViewById<Button>(R.id.button)
        nextBeerBtn.setOnClickListener {
            Ion.with(this)
                .load("https://api.punkapi.com/v2/beers/random")
                .asString()
                .setCallback { e, result -> processBeer(result) }
            }

        }

    //Passes data into methods where they can be extracted
    private fun processBeer(beerData: String) {

        val beerJson = JSONArray(beerData)
        processImage(beerJson)
        processName(beerJson)
//        processStrength(beerData)
//        processTagline(beerData)
//        processDesc(beerData)
//        processPair(beerData)
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


