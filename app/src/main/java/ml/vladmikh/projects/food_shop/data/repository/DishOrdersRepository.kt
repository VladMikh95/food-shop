package ml.vladmikh.projects.food_shop.data.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ml.vladmikh.projects.food_shop.data.local.dao.DishOrderDao
import ml.vladmikh.projects.food_shop.data.local.entities.DishOrder
import javax.inject.Inject

class DishOrdersRepository @Inject constructor (private val dishOrderDao: DishOrderDao) {

    suspend fun insert(dishOrder: DishOrder) = dishOrderDao.insert(dishOrder)

    suspend fun update(dishOrder: DishOrder) = dishOrderDao.update(dishOrder)

    suspend fun delete(dishOrder: DishOrder) = dishOrderDao.delete(dishOrder)

    fun getAllDishOrders() = dishOrderDao.getAllDishOrders()
}