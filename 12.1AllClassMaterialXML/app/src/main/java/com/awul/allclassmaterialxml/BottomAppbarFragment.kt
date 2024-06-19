package com.awul.allclassmaterialxml

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.awul.allclassmaterialxml.databinding.FragmentBottomAppbarBinding
import com.awul.allclassmaterialxml.databinding.FragmentCollapsingToolbarBinding
import com.google.android.material.snackbar.Snackbar


@Suppress("NAME_SHADOWING")
class BottomAppbarFragment : Fragment() {
    private lateinit var binding: FragmentBottomAppbarBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomAppbarBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.fabSecond.setOnClickListener { view ->
            Toast.makeText(activity,"This is FAB action",Toast.LENGTH_SHORT).show()
//            Snackbar.make(view, "This is Snackbar action", Snackbar.LENGTH_INDEFINITE).apply {
//                setAction("Cancel") {
//                    dismiss()
//                }
//                setAnchorView(R.id.fab).show()
//
//            }

        }


        binding.bottomAppBar.setNavigationOnClickListener {
            Toast.makeText(activity, "Bottom Nav Bar Click", Toast.LENGTH_SHORT).show()
        }

        binding.bottomAppBar.setOnMenuItemClickListener {
            when(it.itemId) {

                R.id.nav_gallery -> {
                    Toast.makeText(activity, "Click on gallery", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_home -> {
                    Toast.makeText(activity, "Click on Home Button", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_slideshow -> {
                    Toast.makeText(activity, "Click on slideshow Button", Toast.LENGTH_SHORT).show()
                }

            }
            true
        }


    }
}