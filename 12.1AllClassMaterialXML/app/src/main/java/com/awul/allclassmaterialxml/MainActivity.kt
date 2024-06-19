package com.awul.allclassmaterialxml

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.awul.allclassmaterialxml.databinding.ActivityMainBinding
import com.awul.fragmentallclass.FirstFragment
import com.awul.fragmentallclass.goToFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myToggle: ActionBarDrawerToggle
    private val TAG = "DrawerActivity"
    private val firstFragment: FirstFragment = FirstFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val bundle = Bundle().apply {
            putString("COUNTRY_NAME", "Click a button to go a Class")
        }
        goToFragment(this, bundle, firstFragment)




        setSupportActionBar(binding.toolBar)
        title = "All Class About Material XML"

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
         supportActionBar?.setHomeAsUpIndicator(R.drawable.menu_24)

        binding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                Log.d(TAG, "onDrawerSlide")
            }

            override fun onDrawerOpened(drawerView: View) {
                Log.d(TAG, "onDrawerOpened: ")
            }

            override fun onDrawerClosed(drawerView: View) {
                Log.d(TAG, "onDrawerClosed: ")
            }

            override fun onDrawerStateChanged(newState: Int) {
                Log.d(TAG, "onDrawerStateChanged: ")
            }
        })

        myToggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolBar,
            0, 0).apply {
            isDrawerIndicatorEnabled = true
            syncState()
        }






    }
}