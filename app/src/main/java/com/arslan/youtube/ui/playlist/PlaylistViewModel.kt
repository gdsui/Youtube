package com.arslan.youtube.ui.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arslan.youtube.data.models.PlaylistInfo
import com.arslan.youtube.data.models.PlaylistItem
import com.arslan.youtube.data.network.Resource
import com.arslan.youtube.data.repository.YoutubeRepository

class PlaylistViewModel (private var repository : YoutubeRepository) : ViewModel () {

    var localData = MutableLiveData<MutableList<PlaylistItem>>()

    fun getPlaylists(): LiveData<Resource<PlaylistInfo>> {
        return repository.getPlaylists()
    }

    fun getNextPlaylist(nextPageToken : String): LiveData<Resource<PlaylistInfo>> {
        return repository.getNextPlaylists(nextPageToken)
    }

    fun getPlaylistFromLD(){
        localData.value = repository.getPlaylist()
    }

    fun addPlaylistsToLD(list : MutableList<PlaylistItem>){
        repository.addPlaylists(list)
    }

}