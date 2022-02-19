package com.example.imageviewer.data

import android.content.res.AssetManager
import android.util.Log
import java.io.IOException

class AssetRepo(private val assets: AssetManager) : Repo {

    override fun getImagesJson(): String {
        return try {
            assets.open("data.json").bufferedReader().use { it.readText() }
        }catch (e: IOException){
            Log.e(TAG, "getImagesJson() failed with exception", e)
            ""
        }
    }

    companion object {
        private const val TAG = "Repo"
    }
}