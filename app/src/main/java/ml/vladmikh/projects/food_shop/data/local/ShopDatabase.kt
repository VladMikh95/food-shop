package ml.vladmikh.projects.food_shop.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ml.vladmikh.projects.food_shop.data.local.dao.DishOrderDao
import ml.vladmikh.projects.food_shop.data.local.entities.DishOrder

@Database(entities = [DishOrder::class], version = 1, exportSchema = false)
abstract class ShopDatabase: RoomDatabase() {

    abstract fun dishOrderDao(): DishOrderDao
}