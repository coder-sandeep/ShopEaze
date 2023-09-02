package com.codersandeep.shopeaze

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.codersandeep.shopeaze.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setupWithNavController(navController)
    }
}