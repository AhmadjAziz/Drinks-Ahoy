package com.example.drinksahoy

import android.media.Image
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.JsonArray
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONArray


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

    //Processes the data fetched from punk api and displays it.
    private fun processBeer(beerData: String) {
        val beerJson = JSONArray(beerData)
        val url = beerJson
            .getJSONObject(0)
            .getString("image_url")

        val imgView = findViewById<ImageView>(R.id.imageView)

        Picasso
            .get()
            .load(url)
            .into(imgView)
    }
}


