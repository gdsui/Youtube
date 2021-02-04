package com.arslan.youtube.data.repository

import androidx.lifecycle.liveData
import com.arslan.youtube.data.local.YoutubeDatabase
import com.arslan.youtube.data.models.PlaylistItem
import com.arslan.youtube.data.models.VideoListItem
import com.arslan.youtube.data.network.Resource
import com.arslan.youtube.data.network.YoutubeApi
import kotlinx.coroutines.Dispatchers

class YoutubeRepository(private var api: YoutubeApi, private var database: YoutubeDatabase) {
    companion object {
        const val YOUTUBE_API_KEY = "AIzaSyDr7WJvkOUCrqvY6dsVmV9hjCpxG-EYbiI"
    }

    val channelId = "UC8M5YVWQan_3Elm-URehz9w"
    val part = "snippet,contentDetails"
    val maxResults = 8

    fun getPlaylist(): MutableList<PlaylistItem> {
        return database.wordDao().getPlaylist()
    }

    fun getAllVideos(id: String): VideoListItem? {
        return database.wordDao().getAllVideos(id)
    }

    fun addPlaylists(list: MutableList<PlaylistItem>) {
        database.wordDao().addPlaylist(list)
    }

    fun addVideos(list: MutableList<PlaylistItem>) {
        database.wordDao().addVideos(list)
    }

    fun getPlaylists() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getPlaylists(part, channelId, YOUTUBE_API_KEY, maxResults)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message.toString()))
        }
    }

    fun getNextPlaylists(nextPageToken: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getNextPlaylists(part, channelId, YOUTUBE_API_KEY, maxResults, nextPageToken)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message.toString()))
        }
    }

    fun getVideoListFromPlaylist(videoListId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getVideoListFromPlaylist(part, videoListId, YOUTUBE_API_KEY, maxResults)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message.toString()))
        }
    }

    fun getNextVideoListFromPlaylist(nextPageToken: String, videoListId: String) = liveData(
        Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = api.getNextVideoListFromPlaylist(part, videoListId, YOUTUBE_API_KEY, maxResults, nextPageToken)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message.toString()))
        }
    }
}