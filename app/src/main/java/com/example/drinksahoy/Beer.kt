package com.example.drinksahoy

data class Beer (
    val image_url: String? = null, val name: String? = null, val strength: Int? = null, val tagline: String? = null, val description: String? = null, val food_pair: String? = null
){
     override fun toString(): String {
        return super.toString()
         //TODO use a different delimeter than comma so data can be parsed.
        return "$image_url;$name;$strength;$tagline;$description;$food_pair"

    }
}