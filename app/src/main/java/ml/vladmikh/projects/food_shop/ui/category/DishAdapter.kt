package ml.vladmikh.projects.food_shop.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ml.vladmikh.projects.food_shop.data.network.models.Dishe
import ml.vladmikh.projects.food_shop.databinding.DishItemBinding

class DishAdapter(/*private val onItemClicked: (Dishe) -> Unit*/) : ListAdapter<Dishe, DishAdapter.DishViewHolder>(DiffCallback) {

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
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            //onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DishViewHolder(private var binding: DishItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(dish: Dishe) {

            binding.textViewDish.text = dish.name
            if (dish.image_url != null) {
                binding.imageViewDish.load(dish.image_url.toUri().buildUpon().scheme("https").build())
            }
        }
    }
}
