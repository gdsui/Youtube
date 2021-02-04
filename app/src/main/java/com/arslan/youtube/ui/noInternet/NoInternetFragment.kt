package com.arslan.youtube.ui.noInternet

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.arslan.youtube.R
import com.arslan.youtube.core.BaseFragment
import com.arslan.youtube.extentions.getConnectivityManager
import com.arslan.youtube.extentions.isInternetConnected
import com.arslan.youtube.extentions.showToast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.fragment_no_internet.*
import org.koin.android.ext.android.inject

class NoInternetFragment: BaseFragment<NoInternetViewModel>(R.layout.fragment_no_internet) {
    companion object {
        const val OFFLINE_STATE = "offline_state"
    }

    private fun offline() {
        btn_offline.setOnClickListener {
            var bundle = Bundle()
            bundle.putBoolean(OFFLINE_STATE,true)
            findNavController().navigate(R.id.action_noInternetFragment_to_playlistFragment,bundle)
        }
    }

    private fun isOnline() {
        btn_try_again.setOnClickListener {
            if (isInternetConnected(getConnectivityManager(requireContext()))){
                findNavController().navigateUp()
            }else{
                context?.showToast("No internet")
            }
        }
    }

    override fun getViewModule(): NoInternetViewModel = inject<NoInternetViewModel>().value

    override fun setUpView() {
        isOnline()
        offline()
    }

    override fun setUpViewModelObs() {
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        player: YouTubePlayer?,
        wasRestored: Boolean
    ) {

    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        errorReason: YouTubeInitializationResult?
    ) {
    }
}