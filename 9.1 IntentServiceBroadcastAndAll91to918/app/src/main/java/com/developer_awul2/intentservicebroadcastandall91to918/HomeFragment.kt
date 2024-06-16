package com.developer_awul2.intentservicebroadcastandall91to918

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import com.developer_awul2.intentservicebroadcastandall91to918.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private var navController: NavController? = null
    private val broadcustAction = "com.developer_awul2.sendMyData"
    private val R_CDOE = 101
    private val allPermissions = arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_CONTACTS )

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnIntent.setOnClickListener {
            navController?.navigate(R.id.action_nav_home_to_intentFragment)
        }

        binding.btnService.setOnClickListener {
            navController?.navigate(R.id.action_nav_home_to_serviceFragment)
        }

        binding.btnForeground.setOnClickListener {
            navController?.navigate(R.id.action_nav_home_to_foregroundFragment)
        }

        binding.btnBroadcast2.setOnClickListener {
            broadcastIntent()
        }

        binding.btnContent.setOnClickListener {
            navController?.navigate(R.id.action_nav_home_to_contentProviderFragment)
        }

        binding.btnAlert.setOnClickListener {
            alertDialog(requireContext())
        }

        binding.btnPermission.setOnClickListener {
            requestPermission()
        }



        navController = findNavController(view)



//        navController.navigate(R.id.action_nav_home_to_nav_wholesales)

    }



    override fun onResume() {
        super.onResume()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            activity?.registerReceiver(dataReceiver, IntentFilter(broadcustAction),
                Context.RECEIVER_NOT_EXPORTED)
        }

    }

    override fun onPause() {
        super.onPause()

        try {
            activity?.unregisterReceiver(dataReceiver)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    // Data sender
    private fun broadcastIntent() {
        val intent = Intent().apply {
            action = broadcustAction
            putExtra("send_key", "Custom broadcast receiver by Awul ...!")
        }
        activity?.sendBroadcast(intent)
    }

    // Data receiver
    val dataReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            intent?.action?.let {

                if (it == broadcustAction) {
                    Toast.makeText(context, "Broadcast Receiver 2 is: ${intent?.getStringExtra("send_key")}", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun alertDialog(context: Context) {

        AlertDialog.Builder(context).apply {
            setTitle("Alert Dialog ...!")
            setMessage("This is a Alert dialog Details Info here....!")
            setCancelable(false)
            setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, p1: Int) {
                    dialog?.dismiss()
                    Toast.makeText(context, "You Clicked on No", Toast.LENGTH_SHORT).show()
                }
            })

            setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, p1: Int) {
                    dialog?.dismiss()
                    Toast.makeText(context, " you Clicked on Yes", Toast.LENGTH_SHORT).show()
                }
            })

        }.show()

    }


//    private fun showNotification() {
//
//
//
//        val intent = Intent(this, HomeFragment::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//        val channelId = "my_channel_id"
//        val bm = (resources.getDrawable(R.mipmap.ic_launcher) as Drawable).toBitmap()
//        val builder = NotificationCompat.Builder(this, channelId).apply {
//            setSmallIcon(R.mipmap.ic_launcher)
//            setLargeIcon(bm)
//            setContentTitle("My Notification")
//            setContentText("Hello Notification!")
//            setStyle(NotificationCompat.BigTextStyle().bigText("This is big text This is big text This is big text This is big text ").setSummaryText("#Breaking News"))
//            setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            setContentIntent(pendingIntent)
//            setAutoCancel(true)
//        }
//
//        val notifManager = getSystemService(/* context = */ Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val mChannel = NotificationChannel(channelId, "Tech News", NotificationManager.IMPORTANCE_DEFAULT).apply {
//                description = "Daily tech news update"
//            }
//            notifManager.createNotificationChannel(mChannel)
//        }
//        val rn = Random.nextInt(20, 200)
//        notifManager.notify(rn, builder.build())
//
//    } // Notification




    private fun requestPermission() {

        if (isAllGranted()) {
            Toast.makeText(requireContext(), "All Granted", Toast.LENGTH_SHORT).show()
            // code here
        } else if (needShowRationale()) {

            AlertDialog.Builder(requireContext()).apply {
                setTitle("You mast Need ..!")
                setMessage("If You use this feature ,You need to allow permission")
                setCancelable(false)
                setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0?.dismiss()
                    }
                })
                setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        ActivityCompat.requestPermissions(requireActivity(), allPermissions, R_CDOE)
                        p0?.dismiss()
                    }
                })
            }.show()

        } else {
            ActivityCompat.requestPermissions(requireActivity(), allPermissions, R_CDOE)
        }


    } // requestPermission


    private fun isAllGranted(): Boolean {
        val p = allPermissions.filter { ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED }
        return p.isEmpty()
    } // isAllGranted

    private fun needShowRationale(): Boolean {
        val p = allPermissions.filter { ActivityCompat.shouldShowRequestPermissionRationale(requireActivity(), it) }
        return p.isNotEmpty()
    } // shouldShowRationale

    private fun goToSettings() {
        val intent = Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", `package`, null)
        }
        activity?.startActivity(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == R_CDOE) {
            if (grantResults.contains(PackageManager.PERMISSION_DENIED)) {
                // user denied one or more permissions
                if (!needShowRationale()) {
                    AlertDialog.Builder(requireActivity()).apply {
                        setTitle("You mast Need ..!")
                        setMessage("Go to settings and allow All permissions!")
                        setCancelable(false)
                        setNegativeButton("Cancel", object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                p0?.dismiss()
                            }
                        })
                        setPositiveButton("Settings", object : DialogInterface.OnClickListener {
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                p0?.dismiss()
                                goToSettings()
                            }
                        })
                    }.show()
                }
            } else {
                requestPermission()
            }


        }


    }



}