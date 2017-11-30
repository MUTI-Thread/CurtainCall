package com.newlife.jy.curtaincall.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.dataBean.HorrorComicBean

/**
 * @author JY
 * 2017/11/30 2:59
 */

class HorrorComicAdapter(val context: Context, layoutResId: Int, data: List<HorrorComicBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>?)
    : BaseQuickAdapter<HorrorComicBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean, BaseViewHolder>(layoutResId, data) {


    override fun convert(helper: BaseViewHolder, item: HorrorComicBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean) {
        val s: String? = item.title
        helper.setText(R.id.mTvHorrorComicTitle, s?.replace("-黑白漫话", ""))
        val view: ImageView = helper.getView<View>(R.id.mIvHorrorComicThumb) as ImageView
        Glide.with(context)
                .load(item.thumbnailList!![0])
                .into(view)
    }
}
