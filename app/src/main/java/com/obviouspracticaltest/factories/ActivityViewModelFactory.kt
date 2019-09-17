package com.obviouspracticaltest.factories

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.obviouspracticaltest.viewmodel.NasaPhotosListViewModel


class ActivityViewModelFactory(activity: Activity) : ViewModelProvider.Factory {

    var activity: Activity
    init {
        this.activity = activity
      }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NasaPhotosListViewModel(activity) as T
    }
}