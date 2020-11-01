package com.example.udacity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SimpleFragmentPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {

    private val tabTitles = arrayListOf<String>("Numbers", "Family", "Colors", "Phrases")

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> NumbersFragment()
            1 -> FamilyMembersFragment()
            2 -> ColorsFragment()
            else -> PhrasesFragment()
        }
    }

    override fun getCount(): Int {
            return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position]
    }
}