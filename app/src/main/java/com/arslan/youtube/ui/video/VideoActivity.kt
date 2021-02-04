package com.arslan.youtube.ui.video

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arslan.youtube.R
import com.arslan.youtube.data.models.PlaylistItem
import com.arslan.youtube.data.repository.YoutubeRepository
import com.arslan.youtube.extentions.showToast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    companion object {
        const val VIDEO_ITEM = "video_item"
        const val RECOVERY_REQUEST = 1
    }

    private lateinit var youTubeView: YouTubePlayerView

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RECOVERY_REQUEST) {
            getYouTubePlayerProvider().initialize(YoutubeRepository.YOUTUBE_API_KEY, this)
        }
    }

    fun getYouTubePlayerProvider(): YouTubePlayerView {
        return youTubeView
    }

    private lateinit var video: PlaylistItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        intent?.let {
            video = it.getSerializableExtra(VIDEO_ITEM) as PlaylistItem
        }
        setData()
        initializePlayer()
    }

    private fun initializePlayer() {
        youTubeView = youtube_view as YouTubePlayerView
        youTubeView.initialize(YoutubeRepository.YOUTUBE_API_KEY, this)
    }

    private fun setData() {
        video_title.text = video.snippet?.title
        video_description.text = video.snippet?.description
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if (!wasRestored) {
            player?.cueVideo(video.contentDetails?.videoId); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, errorReason: YouTubeInitializationResult?) {
        if (errorReason!!.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, VideoActivity.RECOVERY_REQUEST).show()
        } else {
            val error: String = String.format(getString(R.string.player_error), errorReason.toString())
            baseContext.showToast(error)
        }
    }

}