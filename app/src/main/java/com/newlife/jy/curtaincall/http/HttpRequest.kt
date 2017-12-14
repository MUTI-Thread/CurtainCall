package com.newlife.jy.curtaincall.http

import android.util.Log
import com.google.gson.Gson
import com.newlife.jy.curtaincall.constant.ComicApi.AUTH
import com.newlife.jy.curtaincall.dataBean.ComicDetailBean
import com.newlife.jy.curtaincall.dataBean.HorrorComicBean
import java.lang.reflect.Type
import java.net.URL

/**
 * @author JY
 * 2017/11/30 1:27
 */

class HttpRequest {

    companion object {

        fun buildUrl(baseUrl: String, map: Map<String, String>): String {
            var query = ""
            for ((k, v) in map) {
                query += "$k=$v&"
            }
            val url = baseUrl + "?" + query + AUTH
            Log.e("RequestUrl:", url)
            return url
        }

        fun <T : Type> getHttpResult(url: String, map: Map<String, String>, clazz: T): T {
            var forcastJsonStr: String? = ""
            try {
                forcastJsonStr = URL(buildUrl(url, map)).readText()
            } catch (e: Exception) {
                Log.e("Error:", e.message)
            }
            val data = Gson().fromJson(forcastJsonStr, clazz.javaClass)
            return data as T
        }

        fun getHorrorComic(baseUrl: String, map: Map<String, String>): List<HorrorComicBean.Contentlist>? {
            val forcastJsonStr: String?
            try {
                forcastJsonStr = URL(buildUrl(baseUrl, map)).readText()
            } catch (e: Exception) {
                return null
            }
            val data = Gson().fromJson(forcastJsonStr, HorrorComicBean.HorrorComic::class.java)
            val contentlist = data.showapi_res_body.pagebean.contentlist
            return if (contentlist.isNotEmpty()) contentlist else null
        }

        fun getHorrorComicDetail(baseUrl: String, map: Map<String, String>): List<String>? {
            val forcastJsonStr: String?
            try {
                forcastJsonStr = URL(buildUrl(baseUrl, map)).readText()
            } catch (e: Exception) {
                return null
            }
            val data = Gson().fromJson(forcastJsonStr, ComicDetailBean.ComicDetail::class.java)
            val imgList = data.showapi_res_body.item.imgList
            return if (imgList.isNotEmpty()) imgList else null
        }
    }

}
