package com.newlife.jy.curtaincall.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * @author JY
 * 2017/11/30 23:38
 */
class GlideUtil private constructor() {

    private object Holder {
        val INSTANCE = GlideUtil()
    }

    companion object {
        val instance: GlideUtil by lazy { Holder.INSTANCE }
    }

    public fun loadImage(context: Context, imageURL: String, imageView: ImageView) {
        Glide.with(context)
                .load(imageURL)
                .into(imageView)
    }

}