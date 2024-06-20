package com.awul.quizapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awul.quizapp.databinding.ActivityMainBinding
import com.awul.quizapp.fragment.AboutUsFragment
import com.awul.quizapp.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        replaceFragment(HomeFragment(), this)

        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId) {

                R.id.home -> {
                    replaceFragment(HomeFragment(), this)
                }

                R.id.info -> {
                    replaceFragment(AboutUsFragment(), this)
                }

            }

            true

        }







    }//onCreate





}