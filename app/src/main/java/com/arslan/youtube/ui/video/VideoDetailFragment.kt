package com.arslan.youtube.ui.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arslan.youtube.R
import com.arslan.youtube.core.BaseFragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import org.koin.android.ext.android.inject

class VideoDetailFragment : BaseFragment<VideoDetailViewModel>(R.layout.fragment_video_detail){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            video = it.getSerializable(VIDEO_ITEM) as PlaylistItem
        }
    }

    override fun getViewModule(): VideoDetailViewModel = inject<VideoDetailViewModel>().value

    override fun setUpView() {
        setData()
        initializePlayer()
    }

    private fun initializePlayer() {

    }

    private fun setData() {

    }

    override fun setUpViewModelObs() {
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        errorReason: YouTubeInitializationResult?
    ) {
        TODO("Not yet implemented")
    }


}