package ml.vladmikh.projects.food_shop.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import ml.vladmikh.projects.food_shop.R
import ml.vladmikh.projects.food_shop.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentStartBinding.inflate(inflater, container, false)

        binding.bakeries.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_categoryFragment, null)
        )

        binding.fastFood.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_categoryFragment, null)
        )

        binding.asian.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_categoryFragment, null)
        )

        binding.soup.setOnClickListener (
            Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_categoryFragment,null)
        )
        return binding.root
    }

}