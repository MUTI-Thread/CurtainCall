package com.newlife.jy.curtaincall.constant

/**
 * @author JY
 * 2017/11/19 16:23
 */

object ComicApi {
    const val APPID = "50308"
    const val SECRET = "418ce308ee0e4c37a593686c936799d2"
    const val AUTH = "&showapi_appid=$APPID&showapi_sign=$SECRET"

    const val COMIC_DIOMAIN = "http://route.showapi.com/"
    const val COMIC_CATEGORY = COMIC_DIOMAIN + "958-1"
    const val COMIC_DETAIL = COMIC_DIOMAIN + "958-2"

    const val KBMH = "/category/weimanhua/kbmh"                 //恐怖漫画
    const val GUSHIMANHUA = "/category/weimanhua/gushimanhua"   //故事漫画
    const val DUANZISHOU = "/category/duanzishou"               //段子手
    const val LENGZHISHI = "/category/lengzhishi"               //冷知识
    const val QIQU = "/category/qiqu"                           //奇趣
    const val DIANYING = "/category/dianying"                   //电影
    const val GAOXIAO = "/category/gaoxiao"                     //搞笑
    const val MENGCHONG = "/category/mengchong"                 //萌宠
    const val XINQI = "/category/xinqi"                         //新奇
    const val HUANQIU = "/category/huanqiu"                     //环球
    const val SHEYING = "/category/sheying"                     //摄影
    const val WANYI = "/category/wanyi"                         //玩艺
    const val CHAHUA = "/category/chahua"                       //插画
}
