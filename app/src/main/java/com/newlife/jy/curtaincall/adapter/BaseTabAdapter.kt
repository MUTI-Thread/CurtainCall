package com.newlife.jy.curtaincall.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

/**
 * @author JY
 * 2017/11/29 2:38
 */
class TextPageAdapter(fm: FragmentManager,
                              val fragments: ArrayList<Fragment>,
                              val titles: Array<String>) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

    override fun getCount(): Int {
        return titles.size
    }
}