package com.developer_awul2.intentservicebroadcastandall91to918

import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.developer_awul2.intentservicebroadcastandall91to918.databinding.FragmentContentProviderBinding
import com.developer_awul2.intentservicebroadcastandall91to918.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class ContentProviderFragment : Fragment() {

    private var _binding: FragmentContentProviderBinding? = null
    private var navController: NavController? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContentProviderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        runBlocking {
            val cont: List<String> = getContactList()
            println("${cont}")
            binding.tvContent.text = "${cont}"
        }

        binding.tvContent.text = ""


        navController = Navigation.findNavController(view)
    } //onViewCreated


    private suspend fun getContactList(): List<String> {
        val result: ArrayList<String> = arrayListOf()
        withContext(Dispatchers.IO) {
            context?.contentResolver?.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null
            )?.let { cursor ->
                while (cursor.moveToNext()) {
                    val name = cursor.getString(cursor.run { getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME) })
                    val phoneNo = cursor.getString(cursor.run { getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER) })
                    result.add("$name $phoneNo")
                }
                cursor.close()
            }
        }
        return result
    } // getContactList



}