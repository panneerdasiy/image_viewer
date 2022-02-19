package com.example.imageviewer.ui.imagegrid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imageviewer.R
import com.example.imageviewer.data.ImageData
import com.example.imageviewer.databinding.ItemImageGridBinding
import com.example.imageviewer.ui.imagegrid.ImagesAdapter.ImageViewHolder
import com.squareup.picasso.Picasso

class ImagesAdapter : ListAdapter<ImageData, ImageViewHolder>(ImagesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ImageViewHolder(private val binding: ItemImageGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ImageData) {
            binding.tvTitle.text = data.title
            Picasso.get()
                .load(data.url)
                .placeholder(R.drawable.place_holder)
                .error(R.drawable.place_holder)
                .centerCrop()
                .fit()
                .into(binding.imageView)
        }
    }



    class ImagesDiffCallback : DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem == newItem
        }
    }
}
