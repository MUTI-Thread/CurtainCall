package com.newlife.jy.curtaincall.http

import android.util.Log
import com.google.gson.Gson
import com.newlife.jy.curtaincall.constant.ComicApi.AUTH
import com.newlife.jy.curtaincall.constant.ComicApi.COMIC_CATEGORY
import com.newlife.jy.curtaincall.constant.ComicApi.KBMH
import com.newlife.jy.curtaincall.dataBean.Contentlist
import com.newlife.jy.curtaincall.dataBean.HorrorComicBean
import java.net.URL

/**
 * @author JY
 * 2017/11/30 1:27
 */

class HttpRequest {

    companion object {
        fun buildBaseUrl(baseUrl: String, type: String, page: Int, maxResult: Int): String {
            val url = buildUrl("$baseUrl?type=$type&page=$page&maxResult=$maxResult")
            Log.e("HttpRequest:", url)
            return url
        }

        fun buildUrl(url: String): String {
            return "$url$AUTH"
        }

        fun getKBMHData(page: Int, maxResult: Int = 10): List<Contentlist>? {
            val forcastJsonStr: String?
            try {
                forcastJsonStr = URL(buildBaseUrl(COMIC_CATEGORY, KBMH, page, maxResult)).readText()
            } catch (e: Exception) {
                return null
            }
            val data = Gson().fromJson(forcastJsonStr, HorrorComicBean::class.java)
            Log.e("HttpRequest:", data.toString())
            val horrorComics: List<Contentlist> = data.showapi_res_body.pagebean.contentlist
            return if (horrorComics.isNotEmpty()) horrorComics else null
        }
    }

}
