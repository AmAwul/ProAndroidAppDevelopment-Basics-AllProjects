package com.awul.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun replaceFragment(fragment: Fragment, activity: FragmentActivity?, bundle: Bundle? = null) {

    val fName = "${fragment::class.simpleName}"

    val frag : Fragment? = activity?.supportFragmentManager?.findFragmentByTag(fName)

    if (frag != null) {
        if (frag.isAdded) {
            return
        }
    }

    bundle?.let {
        fragment.arguments = it
    }

    activity?.supportFragmentManager
        ?.beginTransaction()
        ?.replace(R.id.fragment_container, fragment, fName)
        ?.commit()

}