package com.example.game.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction

inline fun FragmentActivity.replaceFragment(containerViewId: Int, f: () -> Fragment): Fragment? {
    return f().apply { supportFragmentManager.beginTransaction().replace(containerViewId, this).commit() }
}

inline fun FragmentActivity.replaceAddFragment(containerViewId: Int, tag: String, f: () -> Fragment): Fragment? {
    return f().apply { supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(containerViewId, this, tag).addToBackStack(null).commit() }
}