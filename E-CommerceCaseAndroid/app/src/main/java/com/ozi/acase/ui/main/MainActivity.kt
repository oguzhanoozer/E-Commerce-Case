package com.ozi.acase.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels // Bu import'u ekleyin
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.ozi.acase.data.adapter.ProductAdapter
import com.ozi.acase.data.adapter.SliderAdapter
import com.ozi.acase.data.model.Product
import com.ozi.acase.databinding.ActivityMainBinding
import com.ozi.acase.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint


import android.graphics.Rect // Bu import'u ekleyin
import android.view.View // Bu import'u ekleyin
import androidx.recyclerview.widget.RecyclerView // Bu import'u ekleyin
import com.ozi.acase.extensions.showErrorDialog

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter
    private lateinit var sliderAdapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupSlider()
        observeData()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter { product ->
            navigateToDetail(product)
        }

        binding.recyclerViewProducts.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = productAdapter
            addItemDecoration(GridSpacingItemDecoration(2, 8, true))
        }
    }

    private fun setupSlider() {
        sliderAdapter = SliderAdapter { product ->
            navigateToDetail(product)
        }
        binding.viewPagerSlider.adapter = sliderAdapter

        TabLayoutMediator(binding.tabLayoutIndicator, binding.viewPagerSlider) { _, _ -> }.attach()
    }

    private fun observeData() {
        viewModel.sliderProducts.observe(this) { products ->
            products?.let { sliderAdapter.setItems(it) }
        }

        viewModel.gridProducts.observe(this) { products ->
            products?.let { productAdapter.setItems(it) }
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                showErrorDialog(
                    title = "Error",
                    message = it,
                    buttonText = "OK"
                )
            }
        }
    }


    private fun navigateToDetail(product: Product) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("product_id", product.id)
        }
        startActivity(intent)
    }
}