package com.obviouspracticaltest.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.obviouspracticaltest.databinding.NasaPhotosListItemBinding
import com.obviouspracticaltest.models.GalleryModel
import com.obvioustest.listeners.CustomClickListener
import com.obvioustest.ui.PreviewActivity

class NasaPhotosListAdapter(activity: Activity) : RecyclerView.Adapter<NasaPhotosListAdapter.GridHolder>(),
    CustomClickListener {


    var galleryList: List<GalleryModel>
    lateinit var itemGridBinding: NasaPhotosListItemBinding
    var activity: Activity

    init {
        this.activity = activity
        galleryList = ArrayList<GalleryModel>() as List<GalleryModel>
    }

    override fun cardClicked(gallery: GalleryModel, position: Int, imageView: ImageView, textView: TextView) {
        val intent = Intent(activity, PreviewActivity::class.java)
        intent.putExtra("pos", position)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            val p1 = Pair.create<View, String>(imageView, "imageView"+position)
            val p2 = Pair.create<View, String>(textView, "textView"+position)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1, p2)
            activity.startActivity(intent, options.toBundle())
        } else {
            activity.startActivity(intent)
        }
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
        itemGridBinding.imageVar = holder.imageView
        itemGridBinding.textVar = holder.txtTitle
        itemGridBinding.itemClickListener = this
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
    class GridHolder(itemGridBinding: NasaPhotosListItemBinding) : RecyclerView.ViewHolder(itemGridBinding.root) {
        var imageView = itemGridBinding.imageView
        var txtTitle = itemGridBinding.txtTitle

    }


}