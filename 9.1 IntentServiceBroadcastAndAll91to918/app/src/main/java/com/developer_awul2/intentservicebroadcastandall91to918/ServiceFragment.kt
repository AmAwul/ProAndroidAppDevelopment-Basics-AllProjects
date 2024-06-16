package com.developer_awul2.intentservicebroadcastandall91to918

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.developer_awul2.intentservicebroadcastandall91to918.databinding.FragmentServiceBinding
import com.developer_awul2.intentservicebroadcastandall91to918.service.BgService

class ServiceFragment : Fragment() {

    private var _binding: FragmentServiceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentServiceBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBgPlay.setOnClickListener {

            val i = Intent(activity, BgService::class.java)
            activity?.startService(i)
        }

        binding.btnBgStop.setOnClickListener {

            val i = Intent(activity, BgService::class.java)
            activity?.stopService(i)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}