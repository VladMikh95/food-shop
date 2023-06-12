package ml.vladmikh.projects.food_shop.ui.category

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ml.vladmikh.projects.food_shop.databinding.TegItemBinding


class TagAdapter(private val onItemClicked: (Tag) -> Unit) : ListAdapter<Tag, TagAdapter.TagViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Tag>() {
            override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
                return oldItem.isSelected == newItem.isSelected
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val viewHolder = TagViewHolder(
            TegItemBinding.inflate(
                LayoutInflater.from( parent.context),
                parent,
                false
            )
        )

        return viewHolder
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            onItemClicked(getItem(position))
        }
        holder.bind(getItem(position))
    }

    class TagViewHolder(private var binding: TegItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(tag: Tag) {

            binding.textViewTag.text = tag.name

            if (tag.isSelected) {
                binding.cardView.setCardBackgroundColor(Color.parseColor("#FF3364E0"))
                binding.textViewTag.setTextColor(Color.parseColor("#FFFFFFFF"))
            } else {
                binding.cardView.setCardBackgroundColor(Color.parseColor("#FFF8F7F5"))
                binding.textViewTag.setTextColor(Color.parseColor("#FF000000"))
            }
        }
    }
}