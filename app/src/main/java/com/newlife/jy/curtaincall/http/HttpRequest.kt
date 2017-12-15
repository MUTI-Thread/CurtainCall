package com.newlife.jy.curtaincall.http

import android.util.Log
import com.google.gson.Gson
import com.newlife.jy.curtaincall.constant.ComicApi.AUTH
import com.newlife.jy.curtaincall.utils.typeUtils.TypeBuilder
import java.net.URL

/**
 * @author JY
 * 2017/11/30 1:27
 */

object HttpRequest {

    var baseUrl: String = ""
    var params: Map<String, String>? = null

    fun url(baseUrl: String): HttpRequest {
        this.baseUrl = baseUrl
        return this
    }

    fun params(params: Map<String, String>): HttpRequest {
        this.params = params
        return this
    }

    fun buildUrl(): String {
        var query = ""
        if (params != null)
            for ((k, v) in params!!) {
                query += "$k=$v&"
            }
        val url = baseUrl + "?" + query + AUTH
        Log.e("RequestUrl:", url)
        return url
    }


    fun <T> convert(clazz: Class<T>): T {
        var forcastJsonStr: String? = ""
        try {
            forcastJsonStr = URL(buildUrl()).readText()
        } catch (e: Exception) {
            Log.e("Error", e.message)
        }
        val type = TypeBuilder.newInstance(clazz).build()
        return Gson().fromJson(forcastJsonStr, type)
    }

    fun convertString(): String {
        var forcastJsonStr: String? = ""
        try {
            forcastJsonStr = URL(buildUrl()).readText()
        } catch (e: Exception) {
            Log.e("Error", e.message)
        }
        return Gson().fromJson(forcastJsonStr, String::class.java)
    }

}
