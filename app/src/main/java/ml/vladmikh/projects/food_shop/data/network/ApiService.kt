package ml.vladmikh.projects.food_shop.data.network

import ml.vladmikh.projects.food_shop.data.network.models.CategoriesRemoteDataSource
import ml.vladmikh.projects.food_shop.data.network.models.DishesRemoteDataSource
import ml.vladmikh.projects.food_shop.data.network.models.Category
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishesRemoteDataSource(): Response<DishesRemoteDataSource>

    @GET("v3/058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getAllCategoryRemoteDataSource(): Response<CategoriesRemoteDataSource>
}