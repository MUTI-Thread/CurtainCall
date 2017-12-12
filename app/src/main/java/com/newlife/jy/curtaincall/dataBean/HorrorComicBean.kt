package com.newlife.jy.curtaincall.dataBean

/**
 * @author JY
 * 2017/12/3 18:59
 */

class HorrorComicBean {
    data class HorrorComic(
            val showapi_res_code: Int, //0
            val showapi_res_error: String,
            val showapi_res_body: ShowapiResBody
    )

    data class ShowapiResBody(
            val ret_code: Int, //0
            val pagebean: Pagebean
    )

    data class Pagebean(
            val hasMorePage: Boolean, //true
            val contentlist: List<Contentlist>,
            val currentPage: Int, //1
            val maxResult: String //50
    )

    data class Contentlist(
            val id: String, ///weimanhua/kbmh/136275.html
            val thumbnailList: List<String>,
            val time: String, //8:57
            val title: String, //恐怖漫画 | 鱼缸-黑白漫话
            val link: String //http://heibaimanhua.com/weimanhua/kbmh/136275.html
    )
}