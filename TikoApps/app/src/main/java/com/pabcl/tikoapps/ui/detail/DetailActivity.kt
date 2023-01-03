package com.pabcl.tikoapps.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pabcl.tikoapps.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_RATING = "extra_rating"
        const val EXTRA_STOCK = "extra_stock"
        const val EXTRA_BRAND = "extra_brand"
        const val EXTRA_CATEGORY = "extra_category"
        const val EXTRA_THUMBNAIL = "extra_thumbnail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupUIData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupUIData() {
        val extras = intent.extras
        if(extras != null){
            binding.apply {
                tvPrice.text = "$" + extras.getInt(EXTRA_PRICE).toString()
                tvTitle.text = extras.getString(EXTRA_TITLE)
                tvBrand.text = extras.getString(EXTRA_BRAND)
                tvCategory.text = extras.getString(EXTRA_CATEGORY)
                tvStock.text = extras.getInt(EXTRA_STOCK).toString()
                tvRating.text = extras.getFloat(EXTRA_RATING).toString()
                tvDescription.text = extras.getString(EXTRA_DESCRIPTION)

                Glide.with(this@DetailActivity)
                    .load(extras.getString(EXTRA_THUMBNAIL))
                    .into(tvBanner)
            }
        }
    }

}