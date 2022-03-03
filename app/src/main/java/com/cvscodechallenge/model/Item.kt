package com.cvscodechallenge.model


data class Item(
    val title : String,
    val link : String,
    val media : Media,
    val description : String,
    val published : String,
    val author : String,
    val tags : String
)
