package com.example.drinksahoy

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
    var foodPair: String? = null
): Serializable