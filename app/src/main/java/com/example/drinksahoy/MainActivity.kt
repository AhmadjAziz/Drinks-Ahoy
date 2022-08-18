package com.example.drinksahoy

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

        val nextBeerBtn = findViewById<Button>(R.id.button)
        nextBeerBtn.setOnClickListener {
                Ion.with(this)
                    .load("https://api.punkapi.com/v2/beers/random")
                    .asString()
                    .setCallback {ex, result ->
                        processPic(result)

                    }
            }
        }
    private fun processPic(beerData: String){
        val beerJson = JSONArray(beerData)
        // returns the image url of the first item in list of beerJson.
        val url = beerJson
            .getJSONObject(0)
            .getString("url")
        val imgView = findViewById<ImageView>(R.id.imageView)
        Picasso
            .get()
            .load(url)
            .into(imgView)
    }
}