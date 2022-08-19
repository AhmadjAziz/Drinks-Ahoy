package com.example.drinksahoy

data class Beer (
    val image_url: String?,
    val name: String?,
    val strength: Int?,
    val tagline: String?,
    val description: String?,
    val food_pair: String?
){
     override fun toString(): String {
        return super.toString()
         //TODO use a different delimeter than comma so data can be parsed.
        return "$image_url;$name;$strength;$tagline;$description;$food_pair"

    }
}