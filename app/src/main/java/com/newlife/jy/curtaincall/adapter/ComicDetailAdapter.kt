package com.newlife.jy.curtaincall.adapter

import android.content.Context
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newlife.jy.curtaincall.R

/**
 * @author JY
 * 2017/12/13 1:54
 */
class ComicDetailAdapter(val context: Context, data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(data) {

    init {
        this.mLayoutResId = R.layout.item_comic_page
    }

    override fun convert(helper: BaseViewHolder?, item: String?) {
        Glide.with(context)
                .load(item)
                .into(helper?.getView(R.id.image))
    }
}