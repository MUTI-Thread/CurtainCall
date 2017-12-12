package com.newlife.jy.curtaincall.activity

import android.os.Bundle
import com.newlife.jy.curtaincall.R
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * @author JY
 * 2017/12/4 2:08
 */
class ComicDetailActivity : RxAppCompatActivity() {

    var comicId: String = ""

    companion object {
        val COMIC_ID: String = "comic_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        initEvent()
        loadData()
    }

    private fun initEvent() {

    }

    private fun loadData() {
        comicId = intent.getStringExtra(COMIC_ID)
        println(comicId)
    }

}