package com.example.drinksahoy.Activities

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.drinksahoy.BeerData.Beer
import com.example.drinksahoy.R
import com.squareup.picasso.Picasso
import java.io.Serializable

/**
 * The following activity receives processed beer data from the MainActivity and displays a extended beer description.
 * @author Ahmad Aziz
 * @since 21/08/2022
 * @version 1.0
 */
class MoreInfoMenu : AppCompatActivity() {
    //Variable used to store data on beer.
    var currentBeer = Beer()

    companion object{
        const val BEER_COMPARATOR = 5.0
    }

    /**
     * OnCreate of activity, fills up the cardView with extended beer information.
     * @param savedInstanceState reference to a Bundle object that restores activity to a previous state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.more_info_menu)

        //Receives and displays data from the MainActivity.
        currentBeer = intent.extras!!.get(getString(R.string.beerInfo)) as Beer
        fullBearInfo()

        //Returns to the MainActivity and passes the currentBeer data to be displayed.
        val backBtn = findViewById<Button>(R.id.backBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(getString(R.string.beerInfo), currentBeer as Serializable)
            startActivity(intent)
        }
    }

    /**
     * Fills up the cardView with the extended version of the processed beer data.
     */
    private fun fullBearInfo(){
        //Displays image passed through api or presents a image not found icon
        val imgView = findViewById<ImageView>(R.id.beer_image)
        if(currentBeer.imageUrl == getString(R.string.no_img)){
            imgView.setImageResource(R.drawable.no_image_icon)
        } else{
            Picasso
                .get()
                .load(currentBeer.imageUrl)
                .into(imgView)
        }

        //Display beer name
        val nameView = findViewById<TextView>(R.id.name)
        nameView.text = Html.fromHtml("<b>${getString(R.string.name)}</b>${currentBeer.name}")

        //Displays an icon for beers stronger than 5%
        val iconView = findViewById<ImageView>(R.id.warning_icon)
        if (currentBeer.strength!! > BEER_COMPARATOR) {
            iconView.setImageResource(R.drawable.strong_abv)
        } else {
            iconView.setImageDrawable(null)
        }

        //Display the strength of beer
        val strengthView = findViewById<TextView>(R.id.strength)
        strengthView.text = Html.fromHtml("<b>${getString(R.string.strength)}</b>${currentBeer.strength}")

        //Display the tagline of beer.
        val taglineView = findViewById<TextView>(R.id.tagline)
        taglineView.text = Html.fromHtml("<b>${getString(R.string.tagline)}</b>${currentBeer.tagline}")

        //Display the description of beer
        val descriptionView = findViewById<TextView>(R.id.description)
        descriptionView.text = Html.fromHtml("<b>${getString(R.string.description)}</b>${currentBeer.description}")

        //Display the foods that pair with beer
        val foodPairView = findViewById<TextView>(R.id.food_pair)
        foodPairView.text = Html.fromHtml("<b>${getString(R.string.food_pair)}</b>${currentBeer.foodPairToString()}")
    }
}
