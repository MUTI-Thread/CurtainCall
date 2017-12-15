package com.newlife.jy.curtaincall.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.adapter.BaseTabAdapter
import com.newlife.jy.curtaincall.fragment.ComicFragment.Companion.COMIC_TYPE
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A placeholder fragment containing a simple view.
 */
class HomeFragment : Fragment() {

    val tabs: Array<String> = arrayOf("恐怖漫画", "故事漫画")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments: MutableList<Fragment> = ArrayList()
        tabs.forEachIndexed { index, _ ->
            val comicFragment = ComicFragment()
            var bundle = Bundle()
            bundle.putInt(COMIC_TYPE, index)
            comicFragment.arguments = bundle
            fragments.add(comicFragment)
        }
        mViewPager.offscreenPageLimit = tabs.size
        mViewPager.adapter = BaseTabAdapter(childFragmentManager, fragments, tabs)
        mTabLayout.tabMode = TabLayout.MODE_FIXED
        mTabLayout.tabGravity = TabLayout.GRAVITY_FILL
        mTabLayout.setupWithViewPager(mViewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
