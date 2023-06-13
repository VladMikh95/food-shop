package ml.vladmikh.projects.food_shop.ui.basket

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ml.vladmikh.projects.food_shop.data.local.entities.DishOrder
import ml.vladmikh.projects.food_shop.data.repository.DishOrdersRepository
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor (private val repository: DishOrdersRepository): ViewModel() {

    val listDishOrder: LiveData<List<DishOrder>> = repository.getAllDishOrders().asLiveData()

}