package com.example.sample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyFragmentStateAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa)  {

    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment =
        when(position) {
            0 -> {
                FirstFragment()
            }
            1 -> {
                SecondFragment()
            }
            2 -> {
                ThirdFragment()
            }
            else -> {
                FirstFragment()
            }
        }
}