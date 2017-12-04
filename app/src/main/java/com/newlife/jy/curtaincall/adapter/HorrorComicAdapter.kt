package com.newlife.jy.curtaincall.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.dataBean.Contentlist

/**
 * @author JY
 * 2017/11/30 2:59
 */

class HorrorComicAdapter(val context: Context, data: List<Contentlist>?)
    : BaseQuickAdapter<Contentlist, BaseViewHolder>(data) {

    init {
        this.mLayoutResId = R.layout.item_horror_comic
    }

    override fun convert(helper: BaseViewHolder, item: Contentlist) {
        val s: String? = item.title
        helper.setText(R.id.mTvHorrorComicTitle, s?.replace("-黑白漫话", ""))
        val view: ImageView = helper.getView<View>(R.id.mIvHorrorComicThumb) as ImageView
        Glide.with(context)
                .load(item.thumbnailList!![0])
                .into(view)
    }
}
