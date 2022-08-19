package com.example.drinksahoy

import android.content.Intent
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
import kotlinx.coroutines.processNextEventInCurrentThread
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {

    private val currentBeer = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        //displays a random beer onCreate when app is launched
        if(intent.extras == null){
            callAPI()
        }else{

        }

        //button press connects android to punk api to fetch a random beer data
        val nextBeerBtn = findViewById<Button>(R.id.button)
        nextBeerBtn.setOnClickListener {
            callAPI()
            }

        //On card click go into more detail on beer.
        val cardClick = findViewById<CardView>(R.id.beerCard)
        cardClick.setOnClickListener{
            val intent = Intent(this,MoreInfoMenu::class.java)
            intent.putExtra("beer", currentBeer.toString())
            startActivity(intent)
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
        val image_url =  processImage(beerJson)
        val name = processName(beerJson)
        val strength = processStrength(beerJson)
        val tagline = processTagline(beerJson)
        val descData = processDesc(beerJson)
        val pair_data = processPair(beerJson)
        currentBeer = Beer(image_url, name, strength, tagline, descData, pair_data)
    }

    private fun processPair(beerJson: JSONArray):String? {
        val pairData = beerJson
            .getJSONObject(0)
            .getString("food_pairing")
        val nameView = findViewById<TextView>(R.id.food_pair)
        nameView.text = "Food Pairing: $pairData"
        return pairData
    }

    private fun processDesc(beerJson: JSONArray): String? {
        val descData = beerJson
            .getJSONObject(0)
            .getString("description")
        val nameView = findViewById<TextView>(R.id.description)
        nameView.text = "Description: $descData"
        return descData
    }

    private fun processTagline(beerJson: JSONArray): String? {
        val taglineData = beerJson
            .getJSONObject(0)
            .getString("tagline")
        val nameView = findViewById<TextView>(R.id.tagline)
        nameView.text = "Tagline: $taglineData"
        return taglineData
    }

    //processes strength data and extracts it into a string format.
    private fun processStrength(beerJson: JSONArray): Int? {
        val strengthData = beerJson
            .getJSONObject(0)
            .getInt("abv")
        val nameView = findViewById<TextView>(R.id.strength)
        nameView.text = "Strength: $strengthData"
        return strengthData
    }

    //extracts the name of the beer.
    private fun processName(beerJson: JSONArray):String? {

        val nameData = beerJson
            .getJSONObject(0)
            .getString("name")
        val nameView = findViewById<TextView>(R.id.name)
        nameView.text = "Name: $nameData"
        return nameData
    }

    //extracts image url from the Json array and inserts it into the image view.
    private fun processImage(beerJson: JSONArray): String? {

        val image_url = beerJson
            .getJSONObject(0)
            .getString("image_url")
        val imgView = findViewById<ImageView>(R.id.imageView)
        Picasso
            .get()
            .load(image_url)
            .into(imgView)
        return image_url
    }
}


