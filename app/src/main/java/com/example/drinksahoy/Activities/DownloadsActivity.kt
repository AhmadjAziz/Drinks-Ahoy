package com.example.drinksahoy.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drinksahoy.BeerData.Beer
import com.example.drinksahoy.R

class DownloadsActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.downloads_menu)

    }

    private fun populateList(): ArrayList<Beer> {
       //TODO Populate the recyclerView to present List of downloaded beer.
        return ArrayList()
    }
}