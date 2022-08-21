package com.example.drinksahoy

import android.text.Html
import java.io.Serializable

/**
 * The following data class defines the attributes of a beer.
 *@author Ahmad Aziz
 *@since 20/08/2022
 */
data class Beer(
    var imageUrl: String? = null,
    var name: String? = null,
    var strength: Double? = null,
    var tagline: String? = null,
    var description: String? = null,
    var foodPair: ArrayList<String>? = null
): Serializable {
    fun foodPairToString(): String {
        var output = ""
        for (i in foodPair!!) {
            output = output.plus(i)
            output = output.plus(", ")
        }
        return output
    }
}