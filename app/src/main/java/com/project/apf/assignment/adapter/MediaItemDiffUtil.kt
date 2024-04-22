package com.project.apf.assignment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.project.apf.network.models.MediaItem
import javax.inject.Inject

class MediaItemDiffUtil @Inject constructor() : DiffUtil.ItemCallback<MediaItem>() {
    override fun areItemsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MediaItem, newItem: MediaItem): Boolean =
        oldItem == newItem
}