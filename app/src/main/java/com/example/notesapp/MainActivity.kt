package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment

//        navController = findNavController(R.id.fragmentContainerView)


        setupActionBarWithNavController(fragment.navController)
    }

    override fun onNavigateUp(): Boolean {
        return navController.navigateUp() || super.onNavigateUp()
    }

}