package com.obvioustest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.obviouspracticaltest.R
import com.obviouspracticaltest.models.GalleryModel

class MyCustomPagerAdapter(internal var context: Context, internal var galleryList: List<GalleryModel>) :
    PagerAdapter() {
    internal var layoutInflater: LayoutInflater

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return galleryList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = layoutInflater.inflate(R.layout.item_view_preview, container, false)
        val imageView = itemView.findViewById<ImageView>(R.id.imgNasa)
        val txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        val txtDescription = itemView.findViewById<TextView>(R.id.txtDescription)

        val circularProgressDrawable = CircularProgressDrawable(itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(context)
            .load(galleryList[position].url)
            .placeholder(circularProgressDrawable)
            .into(imageView)
        txtTitle.setText(galleryList[position].title)
        txtDescription.setText(galleryList[position].explanation)
        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}
