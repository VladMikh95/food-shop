package ml.vladmikh.projects.food_shop.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.food_shop.databinding.FragmentStartBinding


@AndroidEntryPoint
class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val viewModel by viewModels<StartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentStartBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerViewCategories
        val adapter = StartCategoryAdapter()

        recyclerView.adapter = adapter
        viewModel.getCategoryRemoteDataSource()
        viewModel.categoryList.observe(viewLifecycleOwner, Observer { categories ->
            adapter.submitList(categories)
        })
        return binding.root
    }

}