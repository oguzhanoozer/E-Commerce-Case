package com.ozi.acase.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozi.acase.data.model.Product
import com.ozi.acase.databinding.ItemSliderBinding

class SliderAdapter(
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val items = mutableListOf<Product>()

    inner class SliderViewHolder(private val binding: ItemSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                textViewSliderTitle.text = product.title
                textViewSliderPrice.text = "$ ${product.price}"

                // Rating
                val ratingText = "${product.rating.rate} (${product.rating.count})"
                textViewSliderRating.text = ratingText
                textViewSliderRating.setTextColor(itemView.context.getColor(android.R.color.holo_orange_light))

                // Image
                Glide.with(itemView.context)
                    .load(product.image)
                    .fitCenter()
                    .into(imageViewSlider)

                // Click listener
                root.setOnClickListener {
                    onItemClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = ItemSliderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<Product>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}