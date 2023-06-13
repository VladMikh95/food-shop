package ml.vladmikh.projects.food_shop.ui.dish_dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import coil.load
import ml.vladmikh.projects.food_shop.R
import ml.vladmikh.projects.food_shop.databinding.FragmentDishDialogBinding


class DishDialogFragment : DialogFragment() {

    private val args: DishDialogFragmentArgs by navArgs()
    private lateinit var binding: FragmentDishDialogBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = FragmentDishDialogBinding.inflate(inflater)

        val dishParcel = args.dish
        binding.textViewTitle.text = dishParcel.name
        binding.textViewPrice.text = dishParcel.price.toString() + Html.fromHtml(" &#x20bd")
        binding.textViewWeight.text = dishParcel.weight.toString() + "г"
        binding.textViewDescription.text = dishParcel.description
        binding.imageViewDish.load(dishParcel.image_url.toUri().buildUpon().scheme("https").build())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButtonClose.setOnClickListener { dismiss() }

    }

    override fun getTheme() = R.style.AppTheme_Alert
}