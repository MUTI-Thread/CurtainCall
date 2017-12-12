package com.newlife.jy.curtaincall.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.newlife.jy.curtaincall.R
import com.newlife.jy.curtaincall.activity.ComicDetailActivity
import com.newlife.jy.curtaincall.adapter.HorrorComicAdapter
import com.newlife.jy.curtaincall.constant.ComicApi
import com.newlife.jy.curtaincall.dataBean.Contentlist
import com.newlife.jy.curtaincall.http.ApiClient
import com.zyr.apiclient.network.ApiErrorModel
import com.zyr.apiclient.network.ApiResponse
import com.zyr.apiclient.network.NetworkScheduler
import kotlinx.android.synthetic.main.fragment_horror_comic.*
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
        initAdapter()
        loadData()
        initEvent()
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
                Log.d("tag", (adapter.data as List<Contentlist>)[position].id)
                startActivity(intent)
            }
        }
    }

    private fun loadData() {
        mLoading = true
        val map = mapOf<String, String>("page" to mPage.toString(),
                "maxResult" to "10",
                "showapi_appid" to ComicApi.APPID,
                "showapi_sign" to ComicApi.SECRET,
                "type" to "category/weimanhua/kbmh")
        ApiClient.instance.service.getKBMHRepos(map)                                 //RequestService中的方法
                .compose(NetworkScheduler.compose())                                 //线程切换处理
                .subscribe(object : ApiResponse<List<Contentlist>>(activity) {       //对象表达式约等于Java中的匿名内部类
                    override fun success(data: List<Contentlist>) {
                        Log.d("tag", data.toString())
                        val pos = mData.size
                        when (mPage) {
                            1 -> {
                                mData.clear()
                                mData.addAll(data)
                                mRecyclerView.adapter.notifyDataSetChanged()
                            }
                            else -> {
                                mData.addAll(data)
                                mRecyclerView.adapter.notifyItemRangeInserted(pos, data.size)
                            }
                        }
                    }

                    override fun failure(statusCode: Int, apiErrorModel: ApiErrorModel) { //请求失败，此处直接显示Toast
                        Toast.makeText(activity, apiErrorModel.message, Toast.LENGTH_SHORT).show()
                        Log.d("tag", statusCode.toString())

                    }
                })

//        doAsync {
//            val data = HttpRequest.getKBMHData(mPage)
//            uiThread {
//                mLoading = false
//                if (data == null) {
//                    showSnackbar(view as ViewGroup, "加载失败")
//                    return@uiThread
//                }
//                if (mRecyclerView.adapter == null) {
//                    mData.addAll(data)
//                    mRecyclerView.adapter.notifyDataSetChanged()
//                } else if (mPage > 1) {
//                    val pos = mData.size
//                    mData.addAll(data)
//                    mRecyclerView.adapter.notifyItemRangeInserted(pos, data.size)
//                } else {
//                    mData.clear()
//                    mData.addAll(data)
//                    mRecyclerView.adapter.notifyDataSetChanged()
//                }
//            }
//        }
    }

    private fun initAdapter() {
        mAdapter = HorrorComicAdapter(context, mData)
        mRecyclerView.adapter = mAdapter
    }

}