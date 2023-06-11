package ml.vladmikh.projects.food_shop.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.vladmikh.projects.food_shop.data.network.models.Dishe
import ml.vladmikh.projects.food_shop.data.network.models.DishesRemoteDataSource
import ml.vladmikh.projects.food_shop.data.repository.DishesRepository
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: DishesRepository
) : ViewModel(){

    private lateinit var dishesRemoteDataSource: DishesRemoteDataSource
    private var dishSelected = 0

    private var _dishesList = MutableLiveData<List<Dishe>>()
    val dishesList: LiveData<List<Dishe>> get() = _dishesList

    fun getDishes() {
        viewModelScope.launch {
            dishesRemoteDataSource = repository.getDishesRemoteSource().body()!!



            if (dishSelected == 0) {
                _dishesList.value = dishesRemoteDataSource.dishes
            }

        }
    }

    private fun transformesRemoteDataSource(dishesRemoteDataSource: DishesRemoteDataSource) : List<Dishe> {
        return dishesRemoteDataSource.dishes
    }
}

