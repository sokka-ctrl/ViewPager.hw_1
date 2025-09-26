package com.example.pager.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.pager.R
import com.example.pager.data.local.Pref
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var pref: Pref
    private val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        pref = Pref(this)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        val startDest = if (pref.getFirstOpen() == true) {
            if (auth.currentUser != null) {
                R.id.secondPagerFragment
            } else {
                R.id.signing
            }
        } else {
            R.id.pagerFragment
        }

        navGraph.setStartDestination(startDest)
        navController.graph = navGraph
    }
}
