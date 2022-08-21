package com.example.drinksahoy

import java.io.Serializable

/**
 * The following data class defines the attributes of a beer.
 *@author Ahmad Aziz
 *@since 20/08/2022
 */
data class Beer(
    val imageUrl: String? = null,
    val name: String? = null,
    val strength: Double? = null,
    val tagline: String? = null,
    val description: String? = null,
    val foodPair: String? = null
): Serializable