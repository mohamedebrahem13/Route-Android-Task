package com.example.route_android_task.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.route_android_task.R
import com.example.route_android_task.databinding.ItemProductBinding
import com.example.route_android_task.domain.models.Product

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                // Bind data to the view elements
                textViewTitle.text = product.title
                textViewDescription.text = product.description
                textViewPrice.text = "EGP ${product.price}" // Concatenate "EGP" with price
                textViewDiscount.text = product.discountPercentage.toString()
                // Example: Load product image (replace with your actual logic)
                // Load product image using Coil
                imageViewProduct.load(product.thumbnail) {
                    crossfade(true) // Optional - enable crossfade animation
                    placeholder(R.drawable.place_holder) // Optional - placeholder drawable while loading
                    error(R.drawable.place_holder) // Optional - error drawable if image fails to load
                }                // Set favorite image visibility based on a condition (example)
            }
        }
    }
}