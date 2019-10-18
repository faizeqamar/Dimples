package com.example.datingapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.datingapp.Away
import com.example.datingapp.Nearby


import com.example.datingapp.R

private val TAB_TITLES = arrayOf(
    R.string.nearby,
    R.string.away
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        var main_fragment:Fragment?=null
        var nearby = Nearby()
        var away = Away()
        when(position){
            0->{
                main_fragment =nearby
            }
            1->{
                main_fragment=away
            }
        }
        return main_fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}