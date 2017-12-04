package com.newlife.jy.curtaincall.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.activity.ComicDetailActivity
import com.newlife.jy.curtaincall.activity.showSnackbar
import com.newlife.jy.curtaincall.adapter.HorrorComicAdapter
import com.newlife.jy.curtaincall.dataBean.Contentlist
import com.newlife.jy.curtaincall.http.HttpRequest
import kotlinx.android.synthetic.main.fragment_horror_comic.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import kotlin.properties.Delegates

/**
 * @author JY
 * 2017/11/29 2:50
 */
class HorrorComicFragment : Fragment() {

    private var mData: MutableList<Contentlist> = ArrayList()
    private var mAdapter: HorrorComicAdapter? = null
    private var mPage: Int = 1
    private var mLoading by Delegates.observable(true) { _, _, new ->
        mSwipeRefreshLayout.isRefreshing = new
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_horror_comic, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initEvent()
        loadData()
    }

    private fun initView() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = GridLayoutManager(context, 1)
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


        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            view.setOnClickListener {
                val intent = Intent()
                intent.setClass(activity, ComicDetailActivity::class.java)
                intent.putExtra(ComicDetailActivity.COMIC_ID, (adapter.data as List<Contentlist>)[position].id)
                Log.d("tag",(adapter.data as List<Contentlist>)[position].id)
                startActivity(intent) }
        }
    }

    private fun loadData() {
        mLoading = true
        doAsync {
            val data = HttpRequest.getKBMHData(mPage)
            uiThread {
                mLoading = false
                if (data == null) {
                    showSnackbar(view as ViewGroup, "加载失败")
                    return@uiThread
                }
                if (mRecyclerView.adapter == null) {
                    mData.addAll(data)
                    initAdapter()
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
    }

}