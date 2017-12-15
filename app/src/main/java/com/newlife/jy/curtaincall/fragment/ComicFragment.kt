package com.newlife.jy.curtaincall.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.activity.ComicDetailActivity
import com.newlife.jy.curtaincall.adapter.HorrorComicAdapter
import com.newlife.jy.curtaincall.constant.ComicApi
import com.newlife.jy.curtaincall.dataBean.HorrorComicBean
import com.newlife.jy.curtaincall.http.HttpRequest
import kotlinx.android.synthetic.main.fragment_horror_comic.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.properties.Delegates

/**
 * @author JY
 * 2017/11/29 2:50
 */
class ComicFragment() : Fragment() {

    companion object {
        const val COMIC_TYPE: String = "TYPE"
    }

    private var mIndex: Int = 0
    private var mData: MutableList<HorrorComicBean.Contentlist> = ArrayList()
    private var mAdapter: HorrorComicAdapter? = null
    private var mPage: Int = 1
    private var mLoading by Delegates.observable(true) { _, _, new ->
        mSwipeRefreshLayout.isRefreshing = new
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_horror_comic, container, false)
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        if (args != null) {
            mIndex = args.getInt(COMIC_TYPE)
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAdapter()
        loadData()
        initEvent()
    }

    private fun initView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener {
            mPage = 1
            loadData()
        }

        mRecyclerView.setOnTouchListener { v, event ->
            if (!mLoading && !mRecyclerView.canScrollVertically(1)) {
                mPage++
                loadData()
            }
            false
        }
    }

    private fun loadData() {
        mLoading = true
        doAsync {
            val param = mapOf("type" to ComicApi.LIST_COMIC_TYPE[mIndex], "page" to mPage.toString())
            val comic = HttpRequest
                    .url(ComicApi.COMIC_CATEGORY)//ComicApi.LIST_COMIC_TYPE[comicType]
                    .params(param)
                    .convert(HorrorComicBean.HorrorComic::class.java)
            val data = comic.showapi_res_body.pagebean.contentlist
            uiThread {
                mLoading = false
                if (mRecyclerView.adapter == null) {
                    mData.addAll(data)
                    mRecyclerView.adapter.notifyDataSetChanged()
                } else if (mPage > 1) {
                    val pos = mData.size
                    mData.addAll(data)
                    mRecyclerView.adapter.notifyItemRangeInserted(pos, data.size)
                } else {
                    mData.clear()
                    mData.addAll(data)
                    mRecyclerView.adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun initAdapter() {
        mAdapter = HorrorComicAdapter(context, mData)
        mRecyclerView.adapter = mAdapter
        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, _, position ->
            val intent = Intent()
            intent.setClass(activity, ComicDetailActivity::class.java)
            intent.putExtra(ComicDetailActivity.COMIC_ID, mData[position].id)
            Log.d("tag", mData[position].id)
            startActivity(intent)
        }
    }

}
