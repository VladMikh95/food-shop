package ml.vladmikh.projects.food_shop.data.repository

import ml.vladmikh.projects.food_shop.data.network.ApiService
import javax.inject.Inject

class CategoriesRepository @Inject constructor(private val apiService: ApiService){

    suspend fun getAllCategoryRemoteDataSource() = apiService.getAllCategoryRemoteDataSource()
}