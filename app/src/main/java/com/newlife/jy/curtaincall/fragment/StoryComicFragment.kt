package com.newlife.jy.curtaincall.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newlife.jy.curtaincall.R

/**
 * @author JY
 * 2017/11/29 2:50
 */
class StoryComicFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_story_comic, container, false)
    }

}