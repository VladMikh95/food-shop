package ml.vladmikh.projects.food_shop.ui.dish_dialog

import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.vladmikh.projects.food_shop.data.local.dao.DishOrderDao
import ml.vladmikh.projects.food_shop.data.local.entities.DishOrder
import ml.vladmikh.projects.food_shop.data.network.models.Dishe
import ml.vladmikh.projects.food_shop.data.repository.DishOrdersRepository
import javax.inject.Inject

@HiltViewModel
class DishDialogViewModel @Inject constructor (private val repository: DishOrdersRepository): ViewModel() {

    private fun insertItem(dishOrder: DishOrder) {
        viewModelScope.launch {
            repository.insert(dishOrder)
        }
    }

    private fun getNewDishOrderEntry(dish: Dishe,
                                     count: Int): DishOrder {
        return DishOrder(
            id = dish.id,
            description = dish.description,
            imageUrl = dish.image_url,
            name = dish.name,
            price = dish.price,
            weight = dish.weight,
            count = count)
    }

    fun addNewItem(dish: Dishe, count: Int) {
        val newItem = getNewDishOrderEntry(dish, count)
        insertItem(newItem)
    }
}