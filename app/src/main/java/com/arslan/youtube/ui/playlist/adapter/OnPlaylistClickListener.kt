package com.arslan.youtube.ui.playlist.adapter

import com.arslan.youtube.data.models.PlaylistItem

interface OnPlaylistClickListener {
    fun onClick(item:PlaylistItem)
}