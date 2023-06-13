package ml.vladmikh.projects.food_shop.ui.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ml.vladmikh.projects.food_shop.data.network.models.Category
import ml.vladmikh.projects.food_shop.databinding.CategoryItemBinding

class StartCategoryAdapter : ListAdapter<Category, StartCategoryAdapter.CategoryViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val viewHolder = CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )

        return viewHolder
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            val action = StartFragmentDirections.actionStartFragmentToCategoryFragment(getItem(position).name)
            holder.itemView.findNavController().navigate(action)
        }
    }

    class CategoryViewHolder(private var binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {

            binding.textViewCategory.text = category.name
            binding.imageViewCategory.load(category.image_url.toUri().buildUpon().scheme("https").build())

        }
    }
}