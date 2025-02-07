package com.ozi.acase.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
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


import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ozi.acase.extensions.showErrorDialog
import com.ozi.acase.utils.Constants

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
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) {
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing
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
            isNestedScrollingEnabled = false


            binding.recyclerViewProducts.apply {
                layoutManager = GridLayoutManager(this@MainActivity, 2)
                adapter = productAdapter
                addItemDecoration(GridSpacingItemDecoration(2, 8, true))

                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        val layoutManager = recyclerView.layoutManager as GridLayoutManager
                        val visibleItemCount = layoutManager.childCount
                        val totalItemCount = layoutManager.itemCount
                        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                        if (!binding.progressBar.isVisible) {
                            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                                && firstVisibleItemPosition >= 0
                                && dy > 0
                            ) {
                                viewModel.loadMoreProducts()
                            }
                        }
                    }
                })
            }
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
        binding.progressBar.isVisible = true
        binding.recyclerViewProducts.isVisible = false
        binding.viewPagerSlider.isVisible = false
        binding.textViewEmpty.isVisible = false

        viewModel.sliderProducts.observe(this) { products ->
            if (!products.isNullOrEmpty()) {
                binding.viewPagerSlider.isVisible = true
                sliderAdapter.setItems(products)
            } else {
                binding.viewPagerSlider.isVisible = false
            }
        }

        viewModel.gridProducts.observe(this) { products ->
            if (!products.isNullOrEmpty()) {
                binding.recyclerViewProducts.isVisible = true
                binding.textViewEmpty.isVisible = false
                productAdapter.setItems(products)
            } else {
                binding.recyclerViewProducts.isVisible = false
                binding.textViewEmpty.isVisible = true
            }
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
            if (isLoading) {
                binding.recyclerViewProducts.isVisible = false
                binding.viewPagerSlider.isVisible = false
                binding.textViewEmpty.isVisible = false
            }
        }

        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                showErrorDialog(
                    title = Constants.Dialog.ERROR_TITLE,
                    message = it,
                    buttonText = Constants.Dialog.BUTTON_OK
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