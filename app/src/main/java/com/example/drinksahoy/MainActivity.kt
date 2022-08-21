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


class MainActivity : AppCompatActivity() {

    //Variables used to store data on beer.
    private var currentBeer = Beer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        //displays a random beer onCreate when app is launched
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

        //On card click go into more detail on beer.
        val cardClick = findViewById<CardView>(R.id.beer_card)
        cardClick.setOnClickListener {
            val intent = Intent(this, MoreInfoMenu::class.java)
            intent.putExtra("beerInfo", currentBeer as Serializable)
            startActivity(intent)
        }
    }

    private fun callAPI() {
        Ion.with(this)
            .load("https://api.punkapi.com/v2/beers/random")
            .asString()
            .setCallback { ex, result -> processBeer(result) }
    }

    //Passes data into methods where they can be extracted
    private fun processBeer(beerData: String) {
        val beerJson = JSONArray(beerData)
        processImage(beerJson)
        processName(beerJson)
        processStrength(beerJson)
        processTagline(beerJson)
        processDesc(beerJson)
        processPair(beerJson)
       // currentBeer = Beer(imageUrl, name, strength, tagline, description, foodPair)
        shortBeerInfo()
    }

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

    private fun processDesc(beerJson: JSONArray) {
        currentBeer.description = beerJson
            .getJSONObject(FIRST_INDEX)
            .getString("description")
    }

    private fun processTagline(beerJson: JSONArray) {
        currentBeer.tagline = beerJson
            .getJSONObject(FIRST_INDEX)
            .getString("tagline")
    }

    //processes strength data and extracts it into a string format.
    private fun processStrength(beerJson: JSONArray) {
        currentBeer.strength = beerJson
            .getJSONObject(FIRST_INDEX)
            .getDouble("abv")
    }

    //extracts the name of the beer.
    private fun processName(beerJson: JSONArray) {
        currentBeer.name = beerJson
            .getJSONObject(FIRST_INDEX)
            .getString("name")
    }

    //extracts image url from the Json array and inserts it into the image view.
    private fun processImage(beerJson: JSONArray) {
        currentBeer.imageUrl = beerJson
            .getJSONObject(FIRST_INDEX)
            .getString("image_url")
    }

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