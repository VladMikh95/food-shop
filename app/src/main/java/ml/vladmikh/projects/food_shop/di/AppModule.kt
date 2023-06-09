package ml.vladmikh.projects.food_shop.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ml.vladmikh.projects.food_shop.BuildConfig
import ml.vladmikh.projects.food_shop.data.local.ShopDatabase
import ml.vladmikh.projects.food_shop.data.local.dao.DishOrderDao
import ml.vladmikh.projects.food_shop.data.network.ApiService
import ml.vladmikh.projects.food_shop.data.repository.CategoriesRepository
import ml.vladmikh.projects.food_shop.data.repository.DishOrdersRepository
import ml.vladmikh.projects.food_shop.data.repository.DishesRepository
import ml.vladmikh.projects.food_shop.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl(): String = "https://run.mocky.io/"


    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesDishOrderDao(database: ShopDatabase): DishOrderDao {
        return database.dishOrderDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ShopDatabase {
        return Room.databaseBuilder(
            context,
            ShopDatabase::class.java,
            "shop_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDishesRepository(mainService: ApiService): DishesRepository = DishesRepository(mainService)

    @Provides
    @Singleton
    fun providesCategoriesRepository(mainService: ApiService): CategoriesRepository = CategoriesRepository(mainService)

    @Provides
    @Singleton
    fun providesDishOrdersRepository(dishOrderDao: DishOrderDao): DishOrdersRepository = DishOrdersRepository(dishOrderDao)
}