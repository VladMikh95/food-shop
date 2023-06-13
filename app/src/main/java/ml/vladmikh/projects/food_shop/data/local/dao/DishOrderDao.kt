package ml.vladmikh.projects.food_shop.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ml.vladmikh.projects.food_shop.data.local.entities.DishOrder

@Dao
interface DishOrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dishOrder: DishOrder)

    @Update
    suspend fun update(dishOrder: DishOrder)

    @Delete
    suspend fun delete(dishOrder: DishOrder)

    @Query("SELECT * from dish_order_table")
    fun getAllDishOrders(): Flow<List<DishOrder>>
}