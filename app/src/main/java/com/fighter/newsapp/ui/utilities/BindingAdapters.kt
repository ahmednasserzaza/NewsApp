package com.fighter.newsapp.ui.utilities

import android.view.View
import androidx.databinding.BindingAdapter
import coil.load
import com.fighter.newsapp.R
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

@BindingAdapter(value = ["app:showWhenSearchQueryEmpty"])
fun showWhenListIsEmpty(view: View, text: String) {
    if (text.isEmpty()) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenLoading"])
fun showWhenStetIsLoading(view: View, isLoading: Boolean) {
    if (isLoading) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
