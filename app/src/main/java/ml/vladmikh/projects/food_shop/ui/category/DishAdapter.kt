package ml.vladmikh.projects.food_shop.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ml.vladmikh.projects.food_shop.data.network.models.Dishe
import ml.vladmikh.projects.food_shop.databinding.DishItemBinding


class DishAdapter() : ListAdapter<Dishe, DishAdapter.DishViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Dishe>() {
            override fun areItemsTheSame(oldItem: Dishe, newItem: Dishe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Dishe, newItem: Dishe): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val viewHolder = DishViewHolder(
            DishItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            val action = CategoryFragmentDirections.actionCategoryFragmentToDishDialogFragment(getItem(position))
            holder.itemView.findNavController().navigate(action)
        }
        holder.bind(getItem(position))
    }

    class DishViewHolder(private var binding: DishItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(dish: Dishe) {

            binding.textViewDish.text = dish.name
            binding.imageViewDish.load(dish.image_url.toUri().buildUpon().scheme("https").build())
        }
    }
}
