package com.ozi.acase.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.ozi.acase.data.model.Product
import com.ozi.acase.data.viewModel.DetailViewModel
import com.ozi.acase.databinding.ActivityDetailBinding
import com.ozi.acase.extensions.showErrorDialog
import com.ozi.acase.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        observeData()

        val productId = intent.getIntExtra("product_id", -1)
        if (productId != -1) {
            viewModel.getProductDetail(productId)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(false)
        }
        binding.toolbar.navigationIcon?.setTint(getColor(android.R.color.black))

    }

    private fun observeData() {
        viewModel.product.observe(this) { product ->
            product?.let { setupUI(it) }
        }

        viewModel.loading.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                showErrorDialog(
                    title = Constants.Dialog.ERROR_TITLE,
                    message = it,
                    buttonText = Constants.Dialog.BUTTON_CLOSE
                ) {
                    navigateBack()
                }
            }
        }
    }

    private fun navigateBack() {
        onBackPressed()
    }

    private fun setupUI(product: Product) {
        binding.apply {
            textViewDetailTitle.text = product.title
            textViewDetailPrice.text = "$ ${product.price}"
            textViewDetailRating.text = "${product.rating.rate} (${product.rating.count})"
            textViewDetailCategory.text = product.category
            textViewDetailDescription.text = product.description

            Glide.with(this@DetailActivity)
                .load(product.image)
                .fitCenter()
                .into(imageViewDetail)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
