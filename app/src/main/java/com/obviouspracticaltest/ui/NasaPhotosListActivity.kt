package com.obvioustest.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.obviouspracticaltest.R
import com.obviouspracticaltest.databinding.ActivityGridBinding
import com.obviouspracticaltest.factories.ActivityViewModelFactory
import com.obviouspracticaltest.models.GalleryModel
import com.obviouspracticaltest.viewmodel.NasaPhotosListViewModel

class NasaPhotosListActivity : AppCompatActivity() {

    lateinit var gridBinding: ActivityGridBinding
    lateinit var nasaPhotosListViewModel: NasaPhotosListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)
        gridBinding = DataBindingUtil.setContentView(this, R.layout.activity_grid)
        nasaPhotosListViewModel = ViewModelProviders.of(this, ActivityViewModelFactory(this)).get(NasaPhotosListViewModel::class.java)
        nasaPhotosListViewModel.apiCallForGettingGalleryImage()
        inflateGalleryItems()
    }

    private fun inflateGalleryItems() {
        val galleryObserver = Observer<List<GalleryModel>> { galleryList ->
            Log.e("response size", "" + galleryList.size)

        }
        nasaPhotosListViewModel.galleryModelList.observe(this, galleryObserver)
    }

}