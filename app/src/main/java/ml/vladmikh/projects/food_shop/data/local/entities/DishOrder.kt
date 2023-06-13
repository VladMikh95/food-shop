package ml.vladmikh.projects.food_shop.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish_order_table")
data class DishOrder(

    @PrimaryKey val id: Int,
    val description: String,
    @ColumnInfo(name = "image_url") val imageUrl: String,
    val name: String,
    val price: Int,
    val weight: Int,
    var count: Int
)
