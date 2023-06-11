package ml.vladmikh.projects.food_shop.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
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
        val recyclerView = binding.recyclerViewDishes
        val adapter = DishAdapter()
        recyclerView.adapter = adapter
        viewModel.getDishes()
        viewModel.dishesList.observe(viewLifecycleOwner, Observer { dishes ->
            adapter.submitList(dishes)
        })
        return binding.root

    }


}