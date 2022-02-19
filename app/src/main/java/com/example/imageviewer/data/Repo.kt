package com.example.imageviewer.data

import android.content.res.AssetManager
import java.io.IOException

class Repo(private val assets: AssetManager) {

    fun getImagesJson(): String {
        return try {
            assets.open("data.json").bufferedReader().use { it.readLine() }
        }catch (e: IOException){
//            Logger.e(TAG, "getImagesJson() failed with exception", e)
            ""
        }
    }

    companion object {
        private const val TAG = "Repo"
    }
}