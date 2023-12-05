package com.fighter.newsapp.ui.utilities

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fighter.newsapp.R
import com.fighter.newsapp.ui.base.BaseAdapter
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("app:loadImage")
fun bindNewsImage(image: ShapeableImageView, imageURL: String?) {
    imageURL?.let {
        image.load(imageURL) {
            placeholder(R.drawable.loading)
            error(R.drawable.news_placeholder)
        }
    }
}

@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items: List<T>?) {
    (view.adapter as BaseAdapter<T>?)?.setItems(items ?: emptyList())
    view.scrollToPosition(0)
}