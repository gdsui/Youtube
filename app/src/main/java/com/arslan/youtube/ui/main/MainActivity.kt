package com.arslan.youtube.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.arslan.youtube.R
import com.arslan.youtube.core.BaseActivity
import com.arslan.youtube.extentions.getConnectivityManager
import com.arslan.youtube.extentions.isInternetConnected
import com.arslan.youtube.ui.playlist.adapter.PlaylistAdapter

const val API = "AIzaSyDr7WJvkOUCrqvY6dsVmV9hjCpxG-EYbiI"

class MainActivity : BaseActivity(R.layout.activity_main) {

    lateinit var adapter : PlaylistAdapter

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        checkForInternet()

    }

    private fun checkForInternet() {
        if (!isInternetConnected(getConnectivityManager(baseContext))){
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.action_playlistFragment_to_noInternetFragment)
        }
    }
}