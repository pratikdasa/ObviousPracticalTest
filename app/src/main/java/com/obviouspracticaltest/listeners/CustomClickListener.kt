package com.obvioustest.listeners

import android.view.View
import com.obviouspracticaltest.models.GalleryModel

interface CustomClickListener {
    fun cardClicked(gallery: GalleryModel,pos:Int,view:View)
}