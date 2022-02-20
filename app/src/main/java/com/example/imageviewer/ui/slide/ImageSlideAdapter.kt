package com.example.imageviewer.ui.slide

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imageviewer.R
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.databinding.ItemSlideImageBinding
import com.example.imageviewer.ui.grid.ImagesAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class ImageSlideAdapter(private val picassoCallback: Callback) :
    ListAdapter<ImageData, ImageSlideAdapter.ImageSlideViewHolder>(ImagesAdapter.ImagesDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSlideViewHolder {
        return ImageSlideViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageSlideViewHolder, position: Int) {
        holder.bind(getItem(position), picassoCallback)
    }

    class ImageSlideViewHolder(private val binding: ItemSlideImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ImageData, picassoCallback: Callback) {
            binding.tvTitle.text = data.title
            Picasso.get().load(data.url)
                .noFade()
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .centerInside()
                .fit()
                .into(binding.ivSlide, picassoCallback)

        }

        companion object {
            fun from(parent: ViewGroup): ImageSlideViewHolder {
                return ImageSlideViewHolder(
                    ItemSlideImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }
}
