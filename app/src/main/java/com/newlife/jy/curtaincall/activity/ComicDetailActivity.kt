package com.newlife.jy.curtaincall.activity

import android.os.Bundle
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.adapter.BasePagerAdapter
import com.newlife.jy.curtaincall.http.HttpRequest
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_comic_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * @author JY
 * 2017/12/4 2:08
 */
class ComicDetailActivity : RxAppCompatActivity() {

    var comicId: String = ""
    private var mData: MutableList<String> = ArrayList()

    companion object {
        val COMIC_ID: String = "comic_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        initView();
        initEvent()
        loadData()
    }

    private fun initView() {
//        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initEvent() {

    }

    private fun loadData() {
        comicId = intent.getStringExtra(COMIC_ID)
        doAsync {
            val mData = HttpRequest.getHorrorComicDetail(comicId)
            uiThread {
                mViewPager.adapter = mData?.let { it1 -> BasePagerAdapter(this@ComicDetailActivity, it1) }
//                mRecyclerView.adapter = mData?.let { it1 -> ComicDetailAdapter(this@ComicDetailActivity, it1) }
//                mRecyclerView.adapter.notifyDataSetChanged()
            }
        }
    }

}