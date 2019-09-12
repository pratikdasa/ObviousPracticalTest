package com.obvioustest.di

import com.obviouspracticaltest.viewmodel.NasaPhotosListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface ApiComponent {
        fun  inject(nasaPhotosListViewModel: NasaPhotosListViewModel)
}