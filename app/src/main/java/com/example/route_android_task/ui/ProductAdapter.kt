package com.example.route_android_task.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.route_android_task.R
import com.example.route_android_task.databinding.ItemProductBinding
import com.example.route_android_task.domain.models.Product
import java.text.DecimalFormat

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
                // Calculate old price with discount
                textViewNewPrice.text = itemView.context.getString(R.string.price_format,product.discountedPrice.toString())
                textViewOldPrice.text = itemView.context.getString(R.string.old_price_format, product.price.toString())
                textViewOldPrice.paintFlags = textViewOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                textViewReview.text = itemView.context.getString(R.string.review_format, product.rating)
                // Load product image using Coil
                imageViewProduct.load(product.thumbnail) {
                    crossfade(true)
                    placeholder(R.drawable.place_holder)
                    error(R.drawable.place_holder)
                }
            }


        }

    }
}