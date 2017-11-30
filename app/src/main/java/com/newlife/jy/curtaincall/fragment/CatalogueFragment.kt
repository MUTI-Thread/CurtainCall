package com.newlife.jy.curtaincall.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.adapter.TextPageAdapter
import kotlinx.android.synthetic.main.fragment_catalogue.*

/**
 * A placeholder fragment containing a simple view.
 */
class CatalogueFragment : Fragment() {

    val tabs: Array<String> = arrayOf("恐怖漫画", "故事漫画")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catalogue, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragments: ArrayList<Fragment> = arrayListOf(HorrorComicFragment(), StoryComicFragment())
        mViewPager.offscreenPageLimit = tabs.size
        mViewPager.adapter = TextPageAdapter(childFragmentManager, fragments, tabs)
        mTabLayout.tabMode = TabLayout.MODE_FIXED
        mTabLayout.tabGravity = TabLayout.GRAVITY_FILL
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
