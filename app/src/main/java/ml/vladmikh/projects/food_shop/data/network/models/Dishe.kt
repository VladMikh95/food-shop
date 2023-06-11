package ml.vladmikh.projects.food_shop.data.network.models

data class Dishe(
    val description: String,
    val id: Int,
    val image_url: String,
    val name: String,
    val price: Int,
    val tegs: List<String>,
    val weight: Int
)