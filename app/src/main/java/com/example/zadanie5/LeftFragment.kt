package com.example.zadanie5

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LeftFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class LeftFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val data: SharedPreferences =
            requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val view = inflater.inflate(R.layout.fragment_left, container, false)
        val mpa2 = MyPagerAdapter2(requireActivity())
        val vp2 = view.findViewById<ViewPager2>(R.id.leftViewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.TabLayout)
        vp2.adapter = mpa2
        TabLayoutMediator(tabLayout, vp2
        ) { tab, position ->

            val editor = data.edit()

            when (position) {
                0 -> {
                    tab.text = "RockAlone2K"
                    tab.setIcon(R.drawable.rockalone)
                }
                1 -> {
                    tab.text = "H2P_Gucio"
                    tab.setIcon(R.drawable.gutekziutek)
                }
                2 -> {
                    tab.text = "Cathedral :xdd:"
                    tab.setIcon(R.drawable.cathedralxdd)
                }
            }
            editor.apply()
        }.attach()
        tabLayout.getTabAt(data.getInt("mainImagePref", 0))!!.select()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LeftFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LeftFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TabLayout>(R.id.TabLayout).addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab?) {
            val position = tab?.position ?: 0
            val data: SharedPreferences =
                requireActivity().getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val editor = data.edit()
            when (position) {
                0 -> {
                    editor.putInt("mainImagePref", 0)
                }
                1 -> {
                    editor.putInt("mainImagePref", 1)
                }
                2 ->{
                    editor.putInt("mainImagePref", 2)
                }
            }
                editor.apply()
        }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
        }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
        })
    }
}