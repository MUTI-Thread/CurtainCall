package com.newlife.jy.curtaincall.dataBean

/**
 * @author JY
 * 2017/11/30 1:53
 */

class HorrorComicBean {

    var showapi_res_code: Int = 0
    var showapi_res_error: String? = null
    var showapi_res_body: ShowapiResBodyBean? = null

    class ShowapiResBodyBean {

        var ret_code: Int = 0
        var pagebean: PagebeanBean? = null

        class PagebeanBean {

            var isHasMorePage: Boolean = false
            var currentPage: Int = 0
            var maxResult: String? = null
            var contentlist: List<ContentlistBean>? = null

            class ContentlistBean {

                var id: String? = null
                var time: String? = null
                var title: String? = null
                var link: String? = null
                var thumbnailList: List<String>? = null

                override fun toString(): String {
                    return "ContentlistBean(id=$id, time=$time, title=$title, link=$link, thumbnailList=$thumbnailList)"
                }
            }

            override fun toString(): String {
                return "PagebeanBean(isHasMorePage=$isHasMorePage, currentPage=$currentPage, maxResult=$maxResult, contentlist=$contentlist)"
            }
        }

        override fun toString(): String {
            return "ShowapiResBodyBean(ret_code=$ret_code, pagebean=$pagebean)"
        }

    }

    override fun toString(): String {
        return "HorrorComicBean(showapi_res_code=$showapi_res_code, showapi_res_error=$showapi_res_error, showapi_res_body=$showapi_res_body)"
    }


}
