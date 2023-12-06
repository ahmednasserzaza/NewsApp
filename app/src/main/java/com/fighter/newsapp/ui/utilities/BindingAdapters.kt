package com.fighter.newsapp.ui.utilities

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import com.fighter.newsapp.R
import com.fighter.newsapp.ui.shared.ErrorState
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

@BindingAdapter("app:hideIfTrue")
fun hideIfTrue(view: View, value: Boolean) {
    view.isVisible = !value
}

@BindingAdapter(value = ["app:showIfLoading", "app:showIfHasError"])
fun showIfHasError(view: View, isLoading: Boolean, hasError: Boolean) {
    view.visibility = when {
        isLoading && hasError -> View.GONE
        isLoading -> View.GONE
        hasError -> View.VISIBLE
        else -> View.GONE
    }
}

@BindingAdapter(
    value = [
        "app:hidePlaceHolderIfLoading",
        "app:hidePlaceHolderIfEmptyQuery",
        "app:hidePlaceholderIfError",
        "app:showPlaceholderIfNoItems",
    ]
)
fun handleNoResultPlaceholderVisibility(
    view: View,
    isLoading: Boolean,
    query: String,
    hasError: Boolean,
    itemsCount: Int,
) {
    if ((!isLoading && itemsCount < 1 && query.isEmpty() && !hasError)
        || (!isLoading && itemsCount < 1 && query.isNotEmpty() && !hasError)) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}


@SuppressLint("SetTextI18n")
@BindingAdapter("app:setErrorTextBasedOnError")
fun setErrorTextBasedOnError(view: TextView, errorState: ErrorState) {
    val textError = when (errorState) {
        ErrorState.InternalServer -> "Internal Server Error"
        ErrorState.InvalidData -> "Invalid request data"
        ErrorState.NoConnection -> "No Internet Connection"
        ErrorState.NotFound -> "Information Not found"
        ErrorState.TooManyRequests -> "Too Much Requests try again later"
        ErrorState.UnAuthorized -> "token is unauthorized"
    }
    view.text = textError
}