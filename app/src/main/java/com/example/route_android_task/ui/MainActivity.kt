package com.example.route_android_task.ui

import android.os.Bundle
import android.util.Log
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
class MainActivity : AppCompatActivity() {
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding


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
    }

    private fun displayProducts(products: List<Product>) {
        for (product in products) {
            Log.d("Product", "ID: ${product.id}, Title: ${product.title}, Price: ${product.price}")
            // Add more fields as needed
        }

        // Optionally, you can also log the size of the products list
        Log.d("Product", "Total Products: ${products.size}")

        val adapter = ProductAdapter(products)

        binding.recyclerView.adapter = adapter
    }


    private fun showError(message: String) {
        // Show error message to the user
        Log.d("Product", "error$message")

    }
}



