package com.example.drinksahoy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONArray
import java.io.Serializable


class MainActivity : AppCompatActivity(){

    //Variables used to store data on beer.
    private var imageUrl: String? = null
    private var name: String? = null
    private var strength: Int? = null
    private var tagline: String? = null
    private var description: String? = null
    private var foodPair: String? = null
    private var currentBeer = Beer()

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        //displays a random beer onCreate when app is launched
        if(intent.extras == null){
            callAPI()
        } else{
            currentBeer = intent.extras!!.get("beerInfo") as Beer
            shortBeerInfo()
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
                //TODO Need to improve.
            intent.putExtra("beerInfo", currentBeer as Serializable)
            startActivity(intent)
        }
    }

    private fun callAPI(){
        Ion.with(this)
            .load("https://api.punkapi.com/v2/beers/random")
            .asString()
            .setCallback { ex, result -> processBeer(result) }
    }

    //Passes data into methods where they can be extracted
    private fun processBeer(beerData: String){
        val beerJson = JSONArray(beerData)
        processImage(beerJson)
        processName(beerJson)
        processStrength(beerJson)
        processTagline(beerJson)
        processDesc(beerJson)
        processPair(beerJson)
        currentBeer = Beer(imageUrl, name, strength, tagline, description, foodPair)
        shortBeerInfo()
    }

    private fun processPair(beerJson: JSONArray){
        foodPair = beerJson
            .getJSONObject(0)
            .getString("food_pairing")
    }

    private fun processDesc(beerJson: JSONArray){
        description = beerJson
            .getJSONObject(0)
            .getString("description")
    }

    private fun processTagline(beerJson: JSONArray){
        tagline = beerJson
            .getJSONObject(0)
            .getString("tagline")
    }

    //processes strength data and extracts it into a string format.
    private fun processStrength(beerJson: JSONArray){
        strength = beerJson
            .getJSONObject(0)
            .getInt("abv")
    }

    //extracts the name of the beer.
    private fun processName(beerJson: JSONArray){
        name = beerJson
            .getJSONObject(0)
            .getString("name")
    }

    //extracts image url from the Json array and inserts it into the image view.
    private fun processImage(beerJson: JSONArray){
        imageUrl = beerJson
            .getJSONObject(0)
            .getString("image_url")
    }

    private fun shortBeerInfo(){

        //Display beer image.
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
    }
}


