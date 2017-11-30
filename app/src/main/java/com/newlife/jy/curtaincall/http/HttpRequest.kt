package com.newlife.jy.curtaincall.http

import com.google.gson.Gson
import com.newlife.jy.curtaincall.constant.ComicApi.AUTH
import com.newlife.jy.curtaincall.constant.ComicApi.COMIC_CATEGORY
import com.newlife.jy.curtaincall.constant.ComicApi.COMIC_DIOMAIN
import com.newlife.jy.curtaincall.constant.ComicApi.KBMH
import com.newlife.jy.curtaincall.dataBean.HorrorComicBean
import java.net.URL

/**
 * @author JY
 * 2017/11/30 1:27
 */

class HttpRequest {

    companion object {
        fun buildBaseUrl(baseUrl: String, page: Int, maxResult: Int): String {
            return buildUrl("$baseUrl?page=$page&maxResult=$maxResult")
        }

        fun buildUrl(url: String): String {
            return "$url$AUTH"
        }

        fun getKBMHData(page: Int, maxResult: Int = 10): List<HorrorComicBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean>? {
            var forcastJsonStr: String?
            try {
                forcastJsonStr = URL(buildBaseUrl("$COMIC_DIOMAIN$COMIC_CATEGORY&$KBMH", page, maxResult)).readText()
            } catch (e: Exception) {
                return null
            }
            val data = Gson().fromJson(forcastJsonStr, HorrorComicBean::class.java)
            val horrorComics: List<HorrorComicBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> = data.showapi_res_body?.pagebean?.contentlist!!
            return if (horrorComics.isNotEmpty()) horrorComics else null
        }
    }

}
