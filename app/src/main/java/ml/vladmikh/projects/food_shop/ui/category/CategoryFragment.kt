package ml.vladmikh.projects.food_shop.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.food_shop.R
import ml.vladmikh.projects.food_shop.databinding.FragmentCategoryBinding

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private val viewModel by viewModels<CategoryViewModel>()
    private lateinit var binding: FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        viewModel.getDishes()
        viewModel.dishesRemoteDataSource.observe(viewLifecycleOwner, Observer { dishes ->
            binding.textView.text = dishes.dishes.get(0).name
        })
        return binding.root
    }


}