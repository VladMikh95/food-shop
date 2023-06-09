package ml.vladmikh.projects.food_shop.data.repository

import ml.vladmikh.projects.food_shop.data.network.ApiService
import ml.vladmikh.projects.food_shop.data.network.models.DishesRemoteDataSource
import javax.inject.Inject

class DishesRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getDishesRemoteSource() = apiService.getDishesRemoteDataSource()
}