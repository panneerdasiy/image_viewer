package com.example.imageviewer.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageData(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("copyright")
	val copyright: String,

	@field:SerializedName("media_type")
	val mediaType: String,

	@field:SerializedName("hdurl")
	val hdurl: String,

	@field:SerializedName("service_version")
	val serviceVersion: String,

	@field:SerializedName("explanation")
	val explanation: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("url")
	val url: String
) : Parcelable
