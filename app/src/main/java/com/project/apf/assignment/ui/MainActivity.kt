package com.project.apf.assignment.ui

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.project.apf.assignment.R
import com.project.apf.assignment.base.BaseActivity
import com.project.apf.assignment.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private var navController:NavController?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavController()
    }

    private fun setUpNavController() {
        navController = findNavController(R.id.nav_host_fragment_content_main)
        navController?.let {
            appBarConfiguration = AppBarConfiguration(it.graph)
            binding.toolbar.setupWithNavController(it,appBarConfiguration)
        }
    }

    override fun inflateLayout(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}