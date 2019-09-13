package com.obvioustest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.obviouspracticaltest.R

class ImageLoader {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, imageUrl: String) {
            if(!imageUrl.contains("youtube")){

                val circularProgressDrawable = CircularProgressDrawable(view.context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                Glide.with(view.getContext())
                    .load(imageUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .placeholder(circularProgressDrawable)
                    .into(view)
            }else{
                Glide.with(view.getContext())
                    .load(R.mipmap.ic_launcher)
                    .into(view)
            }

        }
    }
}