package com.codersandeep.shopeaze.models

data class ProductsItemm(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)