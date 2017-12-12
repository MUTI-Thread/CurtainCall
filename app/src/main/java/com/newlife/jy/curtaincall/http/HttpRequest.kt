package com.newlife.jy.curtaincall.http

import android.util.Log
import com.google.gson.Gson
import com.newlife.jy.curtaincall.constant.ComicApi.AUTH
import com.newlife.jy.curtaincall.constant.ComicApi.COMIC_CATEGORY
import com.newlife.jy.curtaincall.constant.ComicApi.COMIC_DETAIL
import com.newlife.jy.curtaincall.constant.ComicApi.KBMH
import com.newlife.jy.curtaincall.dataBean.ComicDetailBean
import com.newlife.jy.curtaincall.dataBean.HorrorComicBean
import java.net.URL

/**
 * @author JY
 * 2017/11/30 1:27
 */

class HttpRequest {

    companion object {
        fun buildComicsUrl(baseUrl: String, type: String, page: Int): String {
            val url = buildUrl("$baseUrl?type=$type&page=$page")
            Log.e("HttpRequest:", url)
            return url
        }

        fun buildComicDetaiUrl(baseUrl: String, comicId: String): String {
            val url = buildUrl("$baseUrl?id=$comicId")
            Log.e("HttpRequest:", url)
            return url
        }

        fun buildUrl(url: String): String {
            return "$url$AUTH"
        }

        fun getHorrorComic(page: Int): List<HorrorComicBean.Contentlist>? {
            val forcastJsonStr: String?
            try {
                forcastJsonStr = URL(buildComicsUrl(COMIC_CATEGORY, KBMH, page)).readText()
            } catch (e: Exception) {
                return null
            }
            val data = Gson().fromJson(forcastJsonStr, HorrorComicBean.HorrorComic::class.java)
            val contentlist = data.showapi_res_body.pagebean.contentlist
            return if (contentlist.isNotEmpty()) contentlist else null
        }

        fun getHorrorComicDetail(comicId: String): List<String>? {
            val forcastJsonStr: String?
            try {
                forcastJsonStr = URL(buildComicDetaiUrl(COMIC_DETAIL, comicId)).readText()
            } catch (e: Exception) {
                return null
            }
            val data = Gson().fromJson(forcastJsonStr, ComicDetailBean.ComicDetail::class.java)
            val imgList = data.showapi_res_body.item.imgList
            return if (imgList.isNotEmpty()) imgList else null
        }
    }

}
