package com.example.almatyparking

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import android.os.Bundle
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.almatyparking.utils.AppPreferences
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppPreferences.setUserId(this, 1)
        AppPreferences.setBalance(this, 2000)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottomNavigationView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {

        return NavigationUI.navigateUp(navController, null)

    }

}