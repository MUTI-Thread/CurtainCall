package com.newlife.jy.curtaincall.dataBean

/**
 * ProjectName:CurtainCall
 * Date:2017/12/12 20:25
 * Created by JY
 */
class ComicDetailBean {
    data class ComicDetail(
            val showapi_res_code: Int, //0
            val showapi_res_error: String,
            val showapi_res_body: ShowapiResBody
    )

    data class ShowapiResBody(
            val ret_code: Int, //0
            val item: Item
    )

    data class Item(
            val time: String, //2年前 (2016-04-26)
            val title: String, //聊斋之《找死》不作就不会死
            val imgList: List<String>
    )
}