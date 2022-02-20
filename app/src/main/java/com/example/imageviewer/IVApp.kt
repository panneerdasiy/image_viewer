package com.example.imageviewer

import android.app.Application
import com.example.imageviewer.data.AssetRepo
import com.google.gson.Gson

class IVApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: IVApp
            private set

        val gson by lazy { Gson() }
        val repo by lazy { AssetRepo(instance.assets, gson) }
    }
}