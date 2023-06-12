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

    private var _tagsList = MutableLiveData<List<Tag>>()
    val tagsList: LiveData<List<Tag>> get() = _tagsList

    fun getDishes() {
        viewModelScope.launch {
            dishesRemoteDataSource = repository.getDishesRemoteSource().body()!!



            if (dishSelected == 0) {
                _dishesList.value = dishesRemoteDataSource.dishes
            }

        }
    }


    //создание списка тегов
    fun createTagList() {
        val listTags = ArrayList<Tag>()
        val listTagNames = getTagsFromDish()

        for (tagName in listTagNames) {
            listTags.add(Tag(tagName, false))
        }
        //Первый тег по умолчанию выбран
        listTags[0].isSelected = true

        _tagsList.value = listTags
    }

    //Получение имен тегов из объектов Dish
    private fun getTagsFromDish(): List<String> {
        val listTagNames = ArrayList<String>()

        for (dish in dishesRemoteDataSource.dishes) {

            val tagNames = dish.tegs

            for (tagName in tagNames) {
                if (!isHasTags(tagName, listTagNames)) {
                    listTagNames.add(tagName)
                }
            }
        }
        return listTagNames
    }

    //Проверка есть ли в списке тегов, тег передоваемый в качестве параметра
    private fun isHasTags (tagName: String, listTags: List<String>): Boolean {

        for (tag in listTags) {

            if (tagName == tag) {
                return true
            }
        }
        return  false
    }

    //Изменяет список блюд dishesList в зависимости от тега
    fun sortDishesByTag(tag: Tag) {
        val dishes = ArrayList<Dishe>()

        for (dish in dishesRemoteDataSource.dishes) {

            if (isHasTags(tag.name, dish.tegs)) {
                dishes.add(dish)
            }
        }

        _dishesList.value = dishes
        changeSelectedTag(tag.name)
    }

    //Изменение выбранного тега в списке
    fun changeSelectedTag(tagName : String) {
        val newListTag = _tagsList.value

        if (newListTag != null) {
            for(newTag in newListTag) {
                newTag.isSelected = newTag.name == tagName
            }


        }
    }
}

