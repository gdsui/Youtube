package com.arslan.youtube.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.arslan.youtube.data.models.PlaylistInfo
import com.arslan.youtube.data.network.Resource
import com.arslan.youtube.data.repository.YoutubeRepository

class DetailsViewModel(private var repository : YoutubeRepository): ViewModel() {
    fun getVideoListFromPlaylist(videoListId : String) : LiveData<Resource<PlaylistInfo>> {
        return repository.getVideoListFromPlaylist(videoListId)
    }

    fun getNextVideoListFromPlaylist(videoListId : String,nextPageToken : String): LiveData<Resource<PlaylistInfo>> {
        return repository.getNextVideoListFromPlaylist(nextPageToken,videoListId)
    }
}