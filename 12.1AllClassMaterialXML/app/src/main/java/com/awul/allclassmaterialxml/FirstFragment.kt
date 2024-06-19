package com.awul.fragmentallclass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.awul.allclassmaterialxml.BottomAppbarFragment
import com.awul.allclassmaterialxml.BottomNavigationFragment
import com.awul.allclassmaterialxml.CollapsingToolbarFragment
import com.awul.allclassmaterialxml.R
import com.awul.allclassmaterialxml.databinding.FragmentFirstBinding
import com.google.android.material.snackbar.Snackbar


class FirstFragment : Fragment() {

  private lateinit var binding: FragmentFirstBinding
    private var countryName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        countryName = arguments?.getString("COUNTRY_NAME", "") ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvFast.text = countryName

        binding.btnNext.setOnClickListener {

//            val bundle = Bundle().apply {
//                putString("COUNTRY_NAME", "$countryName ${binding.edtInput.text.toString()}")
//            }
            val cFragment = CollapsingToolbarFragment()
            goToFragment(activity, null, cFragment)
        }

        binding.btnBottom.setOnClickListener {

            val bFragment = BottomNavigationFragment()
            goToFragment(activity, null, bFragment)
        }
        binding.btnBottomAppBar.setOnClickListener {

            val bfFragment = BottomAppbarFragment()
            goToFragment(activity, null, bfFragment)
        }


        binding.fab.setOnClickListener { view ->
            Snackbar.make(binding.root, "This is Snackbar action", Snackbar.LENGTH_INDEFINITE).apply {
                setAction("Action") {
                    dismiss()
                }
                setAnchorView(R.id.fab).show()

            }

        }


    }

}