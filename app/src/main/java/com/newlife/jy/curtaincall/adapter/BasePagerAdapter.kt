package com.newlife.jy.curtaincall.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.newlife.jy.curtaincall.R

/**
 * ProjectName:CurtainCall
 * Date:2017/12/12 21:43
 * Created by JY
 */
class BasePagerAdapter(val context: Context, val imgs: List<String>) : PagerAdapter() {


    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return imgs.size
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as View?)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.item_comic_page, LinearLayout(context), false)
        val imageView: ImageView = view.findViewById(R.id.image)
        Glide.with(context)
                .load(imgs[position])
                .into(imageView)
        if (imageView.parent != null){
            (imageView.parent as ViewGroup).removeAllViews()
        }
        container.addView(imageView)
        return imageView
    }
}