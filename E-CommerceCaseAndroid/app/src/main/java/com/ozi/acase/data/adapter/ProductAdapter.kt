package com.ozi.acase.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozi.acase.data.model.Product
import com.ozi.acase.databinding.ItemProductBinding

class ProductAdapter(
    private val onItemClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val items = mutableListOf<Product>()

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                textViewTitle.text = product.title
                textViewPrice.text = "$ ${product.price}"

                Glide.with(itemView.context)
                    .load(product.image)
                    .fitCenter()
                    .into(imageViewProduct)

                // CardView'a tıklama olayı ekleyelim
                root.setOnClickListener {
                    onItemClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun setItems(newItems: List<Product>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}