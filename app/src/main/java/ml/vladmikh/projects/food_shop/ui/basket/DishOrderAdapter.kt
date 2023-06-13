package ml.vladmikh.projects.food_shop.ui.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ml.vladmikh.projects.food_shop.data.local.entities.DishOrder
import ml.vladmikh.projects.food_shop.databinding.BasketDishItemBinding

class DishOrderAdapter(private val onItemClicked: (DishOrder) -> Unit) :
    ListAdapter<DishOrder, DishOrderAdapter.DishOrderViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishOrderViewHolder {
        return DishOrderViewHolder(
            BasketDishItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: DishOrderViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

    class DishOrderViewHolder(private var binding: BasketDishItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dishOrder: DishOrder) {
            binding.apply {
                imageViewDish.load(dishOrder.imageUrl.toUri().buildUpon().scheme("https").build())
                textViewTitle.text = dishOrder.name
                textViewPrice.text = dishOrder.price.toString()
                textViewWeight.text = dishOrder.weight.toString()
                textViewCount.text = dishOrder.count.toString()
            }
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<DishOrder>() {
            override fun areItemsTheSame(oldDishOrder: DishOrder, newDishOrder: DishOrder): Boolean {
                return oldDishOrder === newDishOrder
            }

            override fun areContentsTheSame(oldDishOrder: DishOrder, newDishOrder: DishOrder): Boolean {
                return oldDishOrder.count == newDishOrder.count
            }
        }
    }
}