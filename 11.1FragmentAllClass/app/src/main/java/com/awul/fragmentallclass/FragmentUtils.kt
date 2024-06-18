package com.awul.fragmentallclass

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun goToFragment(activity: FragmentActivity?, bundle: Bundle?, fragment: Fragment) {

    val fragmentName: String = fragment.javaClass.simpleName

    bundle?.let {
        fragment.arguments = bundle
    }
    activity?.supportFragmentManager
        ?.beginTransaction()
        ?.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
        ?.addToBackStack(fragmentName)
        ?.add(R.id.fragment_container, fragment, fragmentName)
        ?.commit()

}