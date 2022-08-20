package com.example.drinksahoy

data class Beer (
    val imageUrl: String? = null,
    val name: String? = null,
    val strength: Int? = null,
    val tagline: String? = null,
    val description: String? = null,
    val foodPair: String? = null
){
     override fun toString(): String {
        return super.toString()
         //TODO use a different delimeter than comma so data can be parsed.
        return "$imageUrl;$name;$strength;$tagline;$description;$foodPair"

    }
}