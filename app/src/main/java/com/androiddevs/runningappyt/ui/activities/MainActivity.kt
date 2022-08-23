package com.androiddevs.runningappyt.ui.activities

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.androiddevs.runningappyt.R
import com.androiddevs.runningappyt.db.RunDao
import com.androiddevs.runningappyt.other.Constants.ACTION_SHOW_TRACK_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gotoTrackFragmentIfNeeded(intent)

        setSupportActionBar(toolbar)
        bottomNavigationView.setupWithNavController(nav_host_fragment.findNavController())
        bottomNavigationView.setOnNavigationItemReselectedListener {  }
        nav_host_fragment.findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.runFragment, R.id.settingsFragment, R.id.statisticsFragment ->
                    bottomNavigationView.visibility = View.VISIBLE

                else -> bottomNavigationView.visibility = View.GONE
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        gotoTrackFragmentIfNeeded(intent)
    }

    private fun gotoTrackFragmentIfNeeded(intent: Intent?) {
        if (intent?.action == ACTION_SHOW_TRACK_FRAGMENT) {
            nav_host_fragment.findNavController().navigate(R.id.global_tracking_fragment)
        }
    }
}
