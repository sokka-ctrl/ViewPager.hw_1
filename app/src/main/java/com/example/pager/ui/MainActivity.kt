package com.example.pager.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.pager.R
import com.example.pref.local.Pref

class MainActivity : AppCompatActivity() {


    private lateinit var pref: Pref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        pref = Pref(this)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController

        if (pref.getFirstOpen()) {
            navController.navigate(R.id.secondPagerFragment)
        } else {
            navController.navigate(R.id.pagerFragment)
        }
    }
}