package com.obviouspracticaltest

import android.app.Application
import com.obvioustest.di.ApiComponent
import com.obvioustest.di.ApiModule
import com.obvioustest.di.AppModule
import com.obvioustest.di.DaggerApiComponent
import com.obvioustest.utils.WsParamUtils


class ObviousPracticalTest : Application() {

    lateinit var mApiComponent: ApiComponent
    override fun onCreate() {
        super.onCreate()
        mApiComponent = DaggerApiComponent.builder()
             .appModule(AppModule(this))
             .apiModule( ApiModule(WsParamUtils.BaseUrl))
             .build()
    }

    fun getNetComponent(): ApiComponent {
        return mApiComponent
    }

}