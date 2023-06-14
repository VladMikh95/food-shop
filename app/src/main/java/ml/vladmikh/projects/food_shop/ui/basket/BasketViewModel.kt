package ml.vladmikh.projects.food_shop.ui.basket

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.vladmikh.projects.food_shop.data.local.entities.DishOrder
import ml.vladmikh.projects.food_shop.data.repository.DishOrdersRepository
import ml.vladmikh.projects.food_shop.utils.AppConstants.INCREASING_COUNT
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor (private val repository: DishOrdersRepository): ViewModel() {

    val listDishOrder: LiveData<List<DishOrder>> = repository.getAllDishOrders().asLiveData()

    var price = 0

    fun changeDishOrder(basketChanging: BasketChanging) {

        //определение какая кнопка в элементе recyclerView ыла нажата
        if (basketChanging.countChanging == INCREASING_COUNT) {
            val dishOrder = listDishOrder.value?.get(basketChanging.numDishOrder)
            val count = dishOrder?.count
            val newDishOrder = dishOrder?.copy(count = count?.plus(1) ?: 1)

            if (newDishOrder != null) {
                updateDishOrder(newDishOrder)
            }
        } else {
            val dishOrder = listDishOrder.value?.get(basketChanging.numDishOrder)
            var count = dishOrder?.count
            if (count != null) {
                if (count <= 1) {
                    dishOrder?.let { deleteDishOrder(it) }

                } else {
                    val newDishOrder = dishOrder?.copy(count = count.minus(1))

                    if (newDishOrder != null) {
                        updateDishOrder(newDishOrder)
                    }
                }
            }
        }

    }


    private fun updateDishOrder(dishOrder: DishOrder) {
        viewModelScope.launch {
            repository.update(dishOrder)
        }
    }

    private fun deleteDishOrder(dishOrder: DishOrder) {
        viewModelScope.launch {
            repository.delete(dishOrder)
        }
    }

    //Расчет стоимости товаров в корзине
    fun calculatePrice() {

        price = 0
        val dishOrders = listDishOrder.value
        if (dishOrders != null) {
            for (dishOrder in dishOrders) {
                price += dishOrder.price * dishOrder.count
            }
        }
    }

}