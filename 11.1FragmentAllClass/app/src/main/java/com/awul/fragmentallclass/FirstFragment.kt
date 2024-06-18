package com.awul.fragmentallclass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.awul.fragmentallclass.databinding.FragmentFirstBinding


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

            val bundle = Bundle().apply {
                putString("COUNTRY_NAME", "$countryName ${binding.edtInput.text.toString()}")
            }
            val sFragment = SecondFragment()
            goToFragment(activity, bundle, sFragment)


        }


    }

}