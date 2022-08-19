package com.example.drinksahoy

data class Beer (
    val image_url: String?,
    val name: String?,
    val strength: Int?,
    val tagline: String?,
    val description: String?,
    val food_pair: String?
){
    class Beer(){

    }
    override fun toString(): String {
        return super.toString()
        return "$image_url,$name,$strength,$tagline,$description,$food_pair"
    }
}