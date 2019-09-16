package com.obvioustest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.obviouspracticaltest.R
import com.obviouspracticaltest.databinding.ActivityNasaPhotoListBinding
import com.obviouspracticaltest.factories.ActivityViewModelFactory
import com.obviouspracticaltest.models.GalleryModel
import com.obviouspracticaltest.ui.NasaPhotosListAdapter
import com.obviouspracticaltest.viewmodel.NasaPhotosListViewModel
import com.obvioustest.utils.StaticUtils

class NasaPhotosListActivity : AppCompatActivity() {

    lateinit var gridBinding: ActivityNasaPhotoListBinding
    lateinit var nasaPhotosListViewModel: NasaPhotosListViewModel
    lateinit var gridAdapter: NasaPhotosListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nasa_photo_list)
        gridBinding = DataBindingUtil.setContentView(this, R.layout.activity_nasa_photo_list)
        gridAdapter = NasaPhotosListAdapter(this)
        gridBinding.rvGirid.adapter = gridAdapter
        nasaPhotosListViewModel = ViewModelProviders.of(this, ActivityViewModelFactory(this)).get(NasaPhotosListViewModel::class.java)
        nasaPhotosListViewModel.apiCallForGettingGalleryImage()
        inflateGalleryItems()
    }

    private fun inflateGalleryItems() {
        val galleryObserver = Observer<List<GalleryModel>> { galleryList ->
            gridAdapter.setAdapter(galleryList)
        }
        nasaPhotosListViewModel.galleryModelList.observe(this, galleryObserver)
    }

}