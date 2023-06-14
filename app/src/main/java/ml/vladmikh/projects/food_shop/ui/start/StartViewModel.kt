package ml.vladmikh.projects.food_shop.ui.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ml.vladmikh.projects.food_shop.data.network.models.CategoriesRemoteDataSource
import ml.vladmikh.projects.food_shop.data.network.models.Category
import ml.vladmikh.projects.food_shop.data.repository.CategoriesRepository
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class StartViewModel @Inject constructor(

    private val repository: CategoriesRepository
    ) : ViewModel() {

    private lateinit var categoriesRemoteDataSource: CategoriesRemoteDataSource

    private var _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> get() = _categoryList

    private var _date = getDateNow()
    val date: String get() =_date

    fun getCategoryRemoteDataSource() {
        viewModelScope.launch {
            try {
                categoriesRemoteDataSource = repository.getAllCategoryRemoteDataSource().body()!!
                _categoryList.value  = categoriesRemoteDataSource.сategories
            } catch (e: Exception) {

            }
        }
    }

    private fun getDateNow(): String {
        val calendar =  Calendar.getInstance()
        return  calendar.get(Calendar.DAY_OF_MONTH).toString() + " " + SimpleDateFormat("MMMM").format(calendar.time)+ " " + calendar.get(Calendar.YEAR)
    }

}