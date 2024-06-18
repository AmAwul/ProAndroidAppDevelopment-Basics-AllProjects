package com.awul.fragmentallclass

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.awul.fragmentallclass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val firstFragment: FirstFragment = FirstFragment()


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



        binding.btngo.setOnClickListener {
            val bundle = Bundle().apply {
                putString("COUNTRY_NAME", "Bangladesh")
            }

            goToFragment(this, bundle, firstFragment)

        }



        binding.btnRemove.setOnClickListener {

            supportFragmentManager.apply {
                beginTransaction().remove(firstFragment).commit()
                popBackStack()
            }

        }










    }
}