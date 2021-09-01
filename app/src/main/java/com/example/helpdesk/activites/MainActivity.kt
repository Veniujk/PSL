package com.example.helpdesk.activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.helpdesk.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
               val navView: BottomNavigationView = findViewById(R.id.bottomNavView)
        val navController = findNavController(R.id.mainNavHost)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.raportFragment, R.id.homeFragment, R.id.profileFragment))

               setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


}