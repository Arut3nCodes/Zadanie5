package com.example.zadanie5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

public class MyPagerAdapter2(fa : FragmentActivity): FragmentStateAdapter(fa){
    companion object{
        const val PAGE_COUNT = 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ImageFragment.newInstance("skala","page 1")
            1 -> ImageFragment.newInstance("krakowskikretacz","page 2")
            2 -> ImageFragment.newInstance("ratking","page 3")
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getItemCount(): Int {
        return PAGE_COUNT;
    }
}