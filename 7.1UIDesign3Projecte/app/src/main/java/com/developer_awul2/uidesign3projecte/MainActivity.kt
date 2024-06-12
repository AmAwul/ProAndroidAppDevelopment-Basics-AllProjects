package com.developer_awul2.uidesign3projecte

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.developer_awul2.uidesign3projecte.databinding.ActivityMainBinding
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


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



        binding.tableLayout.setOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                when(position){
                    0 -> {
                        binding.mlClassOne.visibility = View.VISIBLE
                        binding.mlClassTwo.visibility = View.GONE
                        binding.mlClassThree.visibility = View.GONE
                        Toast.makeText(this@MainActivity,"মেনু Linear C1",Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        binding.mlClassOne.visibility = View.GONE
                        binding.mlClassTwo.visibility = View.VISIBLE
                        binding.mlClassThree.visibility = View.GONE
                        Toast.makeText(this@MainActivity,"মেনু Linear C2",Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        binding.mlClassOne.visibility = View.GONE
                        binding.mlClassTwo.visibility = View.GONE
                        binding.mlClassThree.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity,"মেনু Linear C3",Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })





        binding.classTwo.chekBox.addOnCheckedStateChangedListener { checkBox, _ ->
            if (checkBox.isChecked) {
                checkBox.text = "আপনি সম্মত হয়েছেন।"
            } else {
                checkBox.text = "I agree with terms and conditions"
            }
        }




    }
}