package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView


class ToDoMainActivity2 : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_main2)

        //if(savedInstanceState==null){
        setSupportActionBar(findViewById(R.id.my_toobar))
        val fab:View=findViewById(R.id.fab2)
        fab.setOnClickListener { view-> }
        val drawerLayout:DrawerLayout=findViewById(R.id.drawer_layout)
        val navView:NavigationView=findViewById(R.id.nav_view)
        val navHostFragment=supportFragmentManager
            .findFragmentById(R.id.fcv) as NavHostFragment
        val navController=navHostFragment.navController
        appBarConfiguration= AppBarConfiguration(setOf(R.id.nav_todo,R.id.nav_about),drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        }

    override fun onSupportNavigateUp(): Boolean {
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fcv) as NavHostFragment
        val navController=navHostFragment.navController
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    }
