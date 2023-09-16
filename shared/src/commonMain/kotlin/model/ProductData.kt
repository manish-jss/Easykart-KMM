package model

import kotlinx.serialization.Serializable

@Serializable
data class ProductData(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: Rating,
    val title: String
)