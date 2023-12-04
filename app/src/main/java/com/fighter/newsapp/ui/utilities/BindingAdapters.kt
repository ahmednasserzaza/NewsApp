package com.fighter.newsapp.ui.utilities

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.fighter.newsapp.R

@BindingAdapter("app:loadImage")
fun bindNewsImage(image: ImageView, imageURL: String?) {
    imageURL?.let {
        image.load(imageURL) {
            placeholder(R.drawable.loading)
            error(R.drawable.test)
        }
    }
}