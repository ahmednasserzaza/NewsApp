package com.fighter.newsapp.ui.utilities

import androidx.databinding.BindingAdapter
import coil.load
import com.fighter.newsapp.R
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("app:loadImage")
fun bindNewsImage(image: ShapeableImageView, imageURL: String?) {
    imageURL?.let {
        image.load(imageURL) {
            placeholder(R.drawable.loading)
            error(R.drawable.test)
        }
    }
}