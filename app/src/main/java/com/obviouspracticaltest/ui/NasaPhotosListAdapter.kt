package com.obviouspracticaltest.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.obviouspracticaltest.databinding.NasaPhotosListItemBinding
import com.obviouspracticaltest.models.GalleryModel
import com.obvioustest.listeners.CustomClickListener




class NasaPhotosListAdapter(activity: Activity) : RecyclerView.Adapter<NasaPhotosListAdapter.GridHolder>(),
    CustomClickListener {


    var galleryList: List<GalleryModel>
    lateinit var itemGridBinding: NasaPhotosListItemBinding
    var activity: Activity

    init {
        this.activity = activity
        galleryList = ArrayList<GalleryModel>() as List<GalleryModel>
    }

    override fun cardClicked(gallery: GalleryModel, position: Int, view: View) {
    }

    fun setAdapter(galleryList: List<GalleryModel>) {
        this.galleryList = galleryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {
        val inflater = LayoutInflater.from(parent.context)
        itemGridBinding =
            DataBindingUtil.inflate(
                inflater,
                com.obviouspracticaltest.R.layout.nasa_photos_list_item,
                parent,
                false
            ) as NasaPhotosListItemBinding
        return GridHolder(itemGridBinding)
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        val gallery = galleryList.get(position)
        itemGridBinding.gallery = gallery
        itemGridBinding.pos = position
        itemGridBinding.itemClickListener = this
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    class GridHolder(itemGridBinding: NasaPhotosListItemBinding) : RecyclerView.ViewHolder(itemGridBinding.root)

}