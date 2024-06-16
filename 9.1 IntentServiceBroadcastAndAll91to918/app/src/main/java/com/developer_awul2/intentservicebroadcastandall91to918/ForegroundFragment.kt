package com.developer_awul2.intentservicebroadcastandall91to918

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startForegroundService
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.developer_awul2.intentservicebroadcastandall91to918.databinding.FragmentForegroundBinding
import com.developer_awul2.intentservicebroadcastandall91to918.databinding.FragmentHomeBinding
import com.developer_awul2.intentservicebroadcastandall91to918.service.ForegroundService


class ForegroundFragment : Fragment() {

    private var _binding: FragmentForegroundBinding? = null
    private var navController: NavController? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentForegroundBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Inflate the layout for this fragment
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnForegroundPlay.setOnClickListener {
            val i = Intent(activity, ForegroundService::class.java)
            i.putExtra("NAME", "Awul")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                activity?.startForegroundService(i)
            }
        }

        binding.btnForegroundStop.setOnClickListener {
            val i = Intent(activity, ForegroundService::class.java)
            activity?.stopService(i)
        }



        navController = Navigation.findNavController(view)
    }



}