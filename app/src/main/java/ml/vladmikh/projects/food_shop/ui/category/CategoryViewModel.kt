package ml.vladmikh.projects.food_shop.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.vladmikh.projects.food_shop.data.network.models.DishesRemoteDataSource
import ml.vladmikh.projects.food_shop.data.repository.DishesRepository
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: DishesRepository
) : ViewModel(){

    private var _dishesRemoteDataSource = MutableLiveData<DishesRemoteDataSource>()
    val dishesRemoteDataSource: LiveData<DishesRemoteDataSource> get() = _dishesRemoteDataSource

    fun getDishes() {
        viewModelScope.launch {
            _dishesRemoteDataSource.value = repository.getDishesRemoteSource().body()
        }
    }
}

