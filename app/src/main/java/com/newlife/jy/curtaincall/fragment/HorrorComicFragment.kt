package com.newlife.jy.curtaincall.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.activity.showSnackbar
import com.newlife.jy.curtaincall.adapter.HorrorComicAdapter
import com.newlife.jy.curtaincall.dataBean.HorrorComicBean
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

    private var mData: MutableList<HorrorComicBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> = ArrayList()
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
        mRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun initEvent() {
        mSwipeRefreshLayout.setOnRefreshListener {
            mPage = 1
            loadData()
        }

        mRecyclerView.setOnTouchListener { v, event ->
            if (!mLoading && !mRecyclerView.canScrollVertically(1)){
                mPage++
                loadData()
            }
            false
        }
    }

    private fun loadData() {
        mLoading = true
        doAsync {
            val data = HttpRequest.getKBMHData(mPage)
            uiThread {
                mLoading = false
                if (data == null){
                    showSnackbar(view as ViewGroup, "加载失败")
                    return@uiThread
                }
                if (mRecyclerView.adapter == null){
                    mData.addAll(data)
                    initAdapter()
                }
            }
        }
    }

    private fun initAdapter() {
        val adapter = HorrorComicAdapter(R.layout.item_horror_comic,mData)
    }

}