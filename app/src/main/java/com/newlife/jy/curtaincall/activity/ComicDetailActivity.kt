package com.newlife.jy.curtaincall.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.adapter.BasePagerAdapter
import com.newlife.jy.curtaincall.constant.ComicApi
import com.newlife.jy.curtaincall.dataBean.ComicDetailBean
import com.newlife.jy.curtaincall.http.HttpRequest
import kotlinx.android.synthetic.main.activity_comic_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * @author JY
 * 2017/12/4 2:08
 */
class ComicDetailActivity : AppCompatActivity() {

    var comicId: String = ""

    companion object {
        val COMIC_ID: String = "comic_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        loadData()
    }


    private fun loadData() {
        comicId = intent.getStringExtra(COMIC_ID)
        doAsync {
            val param = mapOf("id" to comicId)
            val comicDetail = HttpRequest
                    .url(ComicApi.COMIC_DETAIL)
                    .params(param)
                    .convert(ComicDetailBean.ComicDetail::class.java)
            val  mData = comicDetail.showapi_res_body.item.imgList
            uiThread {
                mViewPager.adapter = mData?.let { it1 -> BasePagerAdapter(this@ComicDetailActivity, it1) }
            }
        }
    }

}