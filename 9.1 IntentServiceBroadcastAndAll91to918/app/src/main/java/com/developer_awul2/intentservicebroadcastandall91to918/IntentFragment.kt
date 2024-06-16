package com.developer_awul2.intentservicebroadcastandall91to918

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.developer_awul2.intentservicebroadcastandall91to918.databinding.FragmentIntentBinding

class IntentFragment : Fragment() {

    private var _binding: FragmentIntentBinding? = null
    private var navController: NavController? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentIntentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnDial.setOnClickListener {

            val intent: Intent = Intent(Intent.ACTION_DIAL).apply {
                setData(Uri.parse("tel:01717720"))

            }
            startActivity(intent)

        }

        binding.btnLocation.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                setData(Uri.parse("geo:24.848397006199338, 89.37246732433883"))
            }
            startActivity(intent)

        }


         // Explicit Intent

        binding.btnBacHome.setOnClickListener {
//            val intent = context.Intent(this, HomeFragment::class.java).also {
//                activity?.startActivity(it)
//            }

            navController?.navigate(R.id.action_intentFragment_to_nav_home)

            }




        navController = Navigation.findNavController(view)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}