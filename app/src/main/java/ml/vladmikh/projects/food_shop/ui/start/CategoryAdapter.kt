package ml.vladmikh.projects.food_shop.ui.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ml.vladmikh.projects.food_shop.R
import ml.vladmikh.projects.food_shop.data.network.models.Category
import ml.vladmikh.projects.food_shop.databinding.CategoryItemBinding

class CategoryAdapter(/*private val onItemClicked: (Category) -> Unit*/) : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback) {

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
        viewHolder.itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_startFragment_to_categoryFragment))
        return viewHolder
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CategoryViewHolder(private var binding: CategoryItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {

            binding.textViewCategory.text = category.name
            if (category.image_url != null) {
                binding.imageViewCategory.load(category.image_url.toUri().buildUpon().scheme("https").build())
            }
        }
    }
}