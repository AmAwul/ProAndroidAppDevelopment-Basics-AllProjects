package com.awul.allclassmaterialxml

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.awul.allclassmaterialxml.databinding.FragmentBottomNavigationBinding
import com.awul.allclassmaterialxml.databinding.FragmentCollapsingToolbarBinding


class BottomNavigationFragment : Fragment() {
    private lateinit var binding: FragmentBottomNavigationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavigationBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNav.setOnItemSelectedListener {

            when(it.itemId) {
                R.id.nav_home -> {

                    Toast.makeText(activity, "Home", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_gallery -> {
                    Toast.makeText(activity, "gallery", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_slideshow -> {
                    Toast.makeText(activity, "Image", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }


    }
}