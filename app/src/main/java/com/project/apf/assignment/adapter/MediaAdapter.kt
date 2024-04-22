package com.project.apf.assignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apf.core.image.loading.photon.Photon
import com.project.apf.assignment.R
import com.project.apf.assignment.databinding.ItemMediaBinding
import com.project.apf.network.models.MediaItem
import javax.inject.Inject

class MediaAdapter @Inject constructor(diffUtil: MediaItemDiffUtil, val photon: Photon) :
    PagingDataAdapter<MediaItem, MediaAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: ItemMediaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(mediaItem: MediaItem) {
            photon.displayImage(
                mediaItem.thumbnail.thumbNailUrl,
                binding.root,
                R.drawable.ic_launcher_background
            )
//            binding.root.load(mediaItem.thumbnail.thumbNailUrl)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMediaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position) ?: return)
    }

}