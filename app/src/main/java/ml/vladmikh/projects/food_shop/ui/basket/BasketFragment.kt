package ml.vladmikh.projects.food_shop.ui.basket

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.food_shop.R
import ml.vladmikh.projects.food_shop.databinding.FragmentBasketBinding

@AndroidEntryPoint
class BasketFragment : Fragment() {

    private lateinit var binding: FragmentBasketBinding
    private val viewModel by viewModels<BasketViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBasketBinding.inflate(inflater, container, false)

        val adapter = DishOrderAdapter {basketChanging ->  viewModel.changeDishOrder(basketChanging)
        }
        binding.recyclerViewBasket.adapter = adapter

        viewModel.listDishOrder.observe(this.viewLifecycleOwner) { items ->
            items.let {
                adapter.submitList(it)
                viewModel.calculatePrice()
                binding.buttonPay.text = (activity?.getString(R.string.button_pay) ?: "") + " " + viewModel.price.toString() + Html.fromHtml(" &#x20bd")
            }
        }

        return binding.root
    }
}