package ml.vladmikh.projects.food_shop.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dishe(
    val description: String = "",
    val id: Int = 0,
    val image_url: String = "",
    val name: String ="",
    val price: Int = 0,
    val tegs: List<String> = ArrayList<String>(),
    val weight: Int = 0
): Parcelable