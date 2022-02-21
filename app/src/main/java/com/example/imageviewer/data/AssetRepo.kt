package com.example.imageviewer.data

import android.content.res.AssetManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.io.IOException

class AssetRepo(private val assets: AssetManager, private val gson: Gson) : Repo {

    override fun getImagesData(): List<ImageData> {
        val json = getImagesJson()
        return parseImagesData(json)
    }

    private fun parseImagesData(json: String): List<ImageData> {
        return try {
            val type = object : TypeToken<List<ImageData>>() {}.type
            gson.fromJson(json, type)
        } catch (e: JsonSyntaxException) {
            Log.e(TAG, "getImagesData() failed with exception", e)
            emptyList()
        }
    }

    private fun getImagesJson(): String {
        return try {
            assets.open("data.json").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            Log.e(TAG, "getImagesJson() failed with exception", e)
            ""
        }
    }

    companion object {
        private const val TAG = "Repo"
    }
}