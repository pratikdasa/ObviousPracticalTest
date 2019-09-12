package com.obvioustest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ImageLoader {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String) {

            Glide.with(view.getContext())
                .load(imageUrl)
                .into(view)
        }


    }
}