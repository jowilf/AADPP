package com.joapp.aadpp.ui.adapter

import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.joapp.aadpp.R
import com.joapp.aadpp.ui.fragment.LeadersFragment

class PagerAdapter(fm: FragmentManager, context: Context) : FragmentStatePagerAdapter(fm) {
    val tabTitles = context.resources.getStringArray(R.array.tabBar)
    override fun getCount() = 2

    override fun getItem(position: Int) = when (position) {
        0 -> LeadersFragment.newInstance()
        else -> LeadersFragment.newInstance(LeadersFragment.FLAG_SKILL)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}