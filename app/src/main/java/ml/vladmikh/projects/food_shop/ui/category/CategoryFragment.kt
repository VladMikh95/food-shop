package ml.vladmikh.projects.food_shop.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.food_shop.databinding.FragmentCategoryBinding

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    companion object {
        val CATEGORY = "category"
    }

    private val viewModel by viewModels<CategoryViewModel>()
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var category: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            category = it.getString(CATEGORY).toString()
        }
        Log.i("abc", category)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCategoryBinding.inflate(inflater, container, false)

        val recyclerViewTegs = binding.recyclerViewTegs
        val tagAdapter = TagAdapter {tag -> viewModel.sortDishesByTag(tag)}
        recyclerViewTegs.adapter = tagAdapter

        val recyclerView = binding.recyclerViewDishes
        val adapter = DishAdapter()
        recyclerView.adapter = adapter

        viewModel.getDishes()
        viewModel.dishesList.observe(viewLifecycleOwner) { dishes ->
            viewModel.createTagList()
            adapter.submitList(dishes)
            tagAdapter.submitList(viewModel.tagsList.value)
        }
        return binding.root

    }

}