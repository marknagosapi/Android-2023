package com.tasty.recipesapp.activities


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.tasty.recipesapp.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main)
        initBottomNavigation()

    }

    private fun initBottomNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navcontroller = navHostFragment.navController

        val navView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        navView.setupWithNavController(navcontroller)

    }
}
