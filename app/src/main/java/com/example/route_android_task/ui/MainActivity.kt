package com.example.route_android_task.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.route_android_task.common.Resource
import com.example.route_android_task.databinding.ActivityMainBinding
import com.example.route_android_task.domain.models.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ProductAdapter.ItemClickListener {
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply window insets to the main layout
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        observeProductState()
    }

    private fun observeProductState() {
        lifecycleScope.launch {
            viewModel.products.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Handle loading state
                        showLoading()

                    }
                    is Resource.Success -> {
                        // Handle success state
                        hideLoading()
                        displayProducts(resource.data)
                    }
                    is Resource.Error -> {
                        // Handle error state
                        hideLoading()
                        showError(resource.message)
                    }
                }
            }
        }
    }

    private fun showLoading() {
        // Show loading indicator or update UI accordingly
        Log.d("Product", "loading")

    }

    private fun hideLoading() {
        // Hide loading indicator or update UI accordingly
        Log.d("Product", "hide_loading")

    }

    private fun displayProducts(products: List<Product>) {
        // Initialize the adapter if not already initialized
        if (!::adapter.isInitialized) {
            adapter = ProductAdapter(products, this)
            binding.recyclerView.adapter = adapter
        } else {
            // Update the list of products using DiffUtil
            adapter.updateProducts(products)
        }
    }


    private fun showError(message: String) {
        // Show error message to the user
        Log.d("Product", "error$message")
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
}

    override fun onItemClick(item: Product) {
        val message = "Product Rating: ${item.rating}"
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()}}



