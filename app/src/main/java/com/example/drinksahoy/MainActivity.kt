package com.example.drinksahoy

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.koushikdutta.ion.Ion
import com.squareup.picasso.Picasso
import org.json.JSONArray
import java.io.Serializable

/**
 * The following activity handles communication with the Punk API that can be found at https://punkapi.com/
 * This consists of connecting, retrieving and processing of beer data.
 * @author Ahmad Aziz
 * @since 21/08/2022
 * @version 1.0
 */
class MainActivity : AppCompatActivity() {
    //Variable used to store data on beer.
    private var currentBeer = Beer()

    /**
     * OnCreate of activity, the app checks for first launch or return from another activity,
     * based off which data is handled.
     * @param savedInstanceState reference to a Bundle object that restores activity to a previous state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        //Checking how to populate data. In this case a null indicates the app launching for the first time.
        if (intent.extras == null) {
            callAPI()
        } else {
            currentBeer = intent.extras!!.get("beerInfo") as Beer
            shortBeerInfo()
        }

        //button press connects android to punk api to fetch a random beer data
        val nextBeerBtn = findViewById<Button>(R.id.next_btn)
        nextBeerBtn.setOnClickListener {
            callAPI()
        }

        //On card click a more detailed card is shown in a new activity.
        val cardClick = findViewById<CardView>(R.id.beer_card)
        cardClick.setOnClickListener {
            val intent = Intent(this, MoreInfoMenu::class.java)
            intent.putExtra("beerInfo", currentBeer as Serializable)
            startActivity(intent)
        }
    }

    /**
     * Connection to Punk Api is made using ION library (Android Asynchronous Networking and Image Loading)
     * found at https://github.com/koush/ion
     */
    private fun callAPI() {
        Ion.with(this)
            .load("https://api.punkapi.com/v2/beers/random")
            .asString()
            .setCallback { ex, result -> processBeer(result) }
    }

    /**
     * Serves as an intermediate hub that passes data for processing.
     * @param beerData contains the JSON data as a string, awaiting to be split.
     */
    private fun processBeer(beerData: String) {
        val beerJson = JSONArray(beerData)
        processImage(beerJson)
        processName(beerJson)
        processStrength(beerJson)
        processTagline(beerJson)
        processDesc(beerJson)
        processPair(beerJson)
        shortBeerInfo()
    }

    /**
     * Processes data received to separate food that can be pairs with the specific beer.
     * @param beerJson contains list of random beers, off which we are interested in first index.
     */
    private fun processPair(beerJson: JSONArray) {
        val beerPair = beerJson
        .getJSONObject(FIRST_INDEX)
            .getJSONArray("food_pairing")

        val list = arrayListOf<String>()
        for (i in 0 until beerPair.length()) {
            list.add(beerPair.getString(i))
        }
        currentBeer.foodPair = list
    }

    /**
     * Processes data received to separate the description of a specific beer.
     * @param beerJson contains list of random beers, off which we are interested in first index.
     */
    private fun processDesc(beerJson: JSONArray) {
        currentBeer.description = beerJson
            .getJSONObject(FIRST_INDEX)
            .getString("description")
    }

    /**
     * Processes data received to separate the tagline of a specific beer.
     * @param beerJson contains list of random beers, off which we are interested in first index.
     */
    private fun processTagline(beerJson: JSONArray) {
        currentBeer.tagline = beerJson
            .getJSONObject(FIRST_INDEX)
            .getString("tagline")
    }

    /**
     * Processes data received to separate the strength of a specific beer.
     * @param beerJson contains list of random beers, off which we are interested in first index.
     */
    private fun processStrength(beerJson: JSONArray) {
        currentBeer.strength = beerJson
            .getJSONObject(FIRST_INDEX)
            .getDouble("abv")
    }

    /**
     * Processes data received to separate the name of a specific beer.
     * @param beerJson contains list of random beers, off which we are interested in first index.
     */
    private fun processName(beerJson: JSONArray) {
        currentBeer.name = beerJson
            .getJSONObject(FIRST_INDEX)
            .getString("name")
    }

    /**
     * Processes data received to separate the image of a specific beer.
     * @param beerJson contains list of random beers, off which we are interested in first index.
     */
    private fun processImage(beerJson: JSONArray) {
        currentBeer.imageUrl = beerJson
            .getJSONObject(FIRST_INDEX)
            .getString("image_url")
    }

    /**
     * Fills up the cardView with the short version of the processed beer data.
     */
    private fun shortBeerInfo() {
        //Displays image passed through api or presents a image not found icon
        val imgView = findViewById<ImageView>(R.id.beer_image)
        if(currentBeer.imageUrl == "null"){
            imgView.setImageResource(R.drawable.no_image_icon)
        }else{
            Picasso
                .get()
                .load(currentBeer.imageUrl)
                .into(imgView)
        }

        //Display beer name
        val nameView = findViewById<TextView>(R.id.name)
        nameView.text = Html.fromHtml("<b>Name: </b>${currentBeer.name}")

        //Displays an icon for beers stronger than 5%
        val iconView = findViewById<ImageView>(R.id.warning_icon)
        if (currentBeer.strength!! > BEER_COMPARATOR) {
            iconView.setImageResource(R.drawable.strong_abv)
        } else {
            iconView.setImageDrawable(null)
        }

        //Display the strength of beer
        val strengthView = findViewById<TextView>(R.id.strength)
        strengthView.text = Html.fromHtml("<b>Strength: </b>${currentBeer.strength}")

        //Display the tagline of beer.
        val taglineView = findViewById<TextView>(R.id.tagline)
        taglineView.text = Html.fromHtml("<b>Tagline: </b>${currentBeer.tagline}")
    }
}