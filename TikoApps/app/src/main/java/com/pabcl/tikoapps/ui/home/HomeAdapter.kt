package com.pabcl.tikoapps.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.core.util.Pair
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pabcl.tikoapps.R
import com.pabcl.tikoapps.data.local.ProductsEntity
import com.pabcl.tikoapps.databinding.ItemProductsBinding
import com.pabcl.tikoapps.ui.detail.DetailActivity


class HomeAdapter(private val onFavoriteClick: (ProductsEntity) -> Unit): ListAdapter<ProductsEntity, HomeAdapter.ProductsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(ItemProductsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val products = getItem(position)
        holder.bind(products)

        val isFavorite = holder.binding.btnFavorite
        if (products.isFavorite) {
            isFavorite.setImageDrawable(ContextCompat.getDrawable(isFavorite.context, R.drawable.ic_menu_favorite))
        } else {
            isFavorite.setImageDrawable(ContextCompat.getDrawable(isFavorite.context, R.drawable.ic_favorite_border))
        }
        isFavorite.setOnClickListener {
            onFavoriteClick(products)
        }
    }

    class ProductsViewHolder(val binding: ItemProductsBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(products: ProductsEntity) {
            binding.apply {
                tvTitle.text = products.title
                tvPrice.text = "$" + products.price.toString()
                tvRating.text = products.rating.toString()
                tvStock.text = products.stock.toString()

                Glide.with(itemView.context)
                    .load(products.thumbnail)
                    .into(tvThumbnail)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TITLE, products.title)
                    intent.putExtra(DetailActivity.EXTRA_PRICE, products.price)
                    intent.putExtra(DetailActivity.EXTRA_BRAND, products.brand)
                    intent.putExtra(DetailActivity.EXTRA_CATEGORY, products.category)
                    intent.putExtra(DetailActivity.EXTRA_DESCRIPTION, products.description)
                    intent.putExtra(DetailActivity.EXTRA_RATING, products.rating)
                    intent.putExtra(DetailActivity.EXTRA_STOCK, products.stock)
                    intent.putExtra(DetailActivity.EXTRA_THUMBNAIL, products.thumbnail)

                    val optionsCompat: ActivityOptionsCompat =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            itemView.context as Activity,
                            Pair(tvThumbnail, "banner"),
                            Pair(tvPrice, "tvPrice"),
                            Pair(tvTitle, "tvTitle")
                        )
                    itemView.context.startActivity(intent, optionsCompat.toBundle())
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<ProductsEntity> =
            object : DiffUtil.ItemCallback<ProductsEntity>() {
                override fun areItemsTheSame(oldProducts: ProductsEntity, newProducts: ProductsEntity): Boolean {
                    return oldProducts.title == newProducts.title
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldProducts: ProductsEntity, newProducts: ProductsEntity): Boolean {
                    return oldProducts == newProducts
                }
            }
    }
}