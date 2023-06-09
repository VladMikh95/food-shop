package ml.vladmikh.projects.food_shop.data.network

import ml.vladmikh.projects.food_shop.data.network.models.DishesRemoteDataSource
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishesRemoteDataSource(): Response<DishesRemoteDataSource>
}