package com.obvioustest.listeners

import android.widget.ImageView
import android.widget.TextView
import com.obviouspracticaltest.models.GalleryModel

interface CustomClickListener {
    fun cardClicked(gallery: GalleryModel, pos:Int, imageView: ImageView, textView: TextView)
}