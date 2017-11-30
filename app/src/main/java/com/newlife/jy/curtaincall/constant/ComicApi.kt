package com.newlife.jy.curtaincall.constant

/**
 * @author JY
 * 2017/11/19 16:23
 */

object ComicApi {
    public val APPID = "50308"
    public val SECRET = "418ce308ee0e4c37a593686c936799d2"
    public val AUTH = "&showapi_appid=$APPID&showapi_sign=$SECRET"

    public val COMIC_DIOMAIN = "http://route.showapi.com/"
    public val COMIC_CATEGORY = COMIC_DIOMAIN + "958-1"
    public val COMIC_DETAIL = COMIC_DIOMAIN + "958-2"

    public val KBMH = "/category/weimanhua/kbmh"                 //恐怖漫画
    public val GUSHIMANHUA = "/category/weimanhua/gushimanhua"   //故事漫画
    public val DUANZISHOU = "/category/duanzishou"               //段子手
    public val LENGZHISHI = "/category/lengzhishi"               //冷知识
    public val QIQU = "/category/qiqu"                           //奇趣
    public val DIANYING = "/category/dianying"                   //电影
    public val GAOXIAO = "/category/gaoxiao"                     //搞笑
    public val MENGCHONG = "/category/mengchong"                 //萌宠
    public val XINQI = "/category/xinqi"                         //新奇
    public val HUANQIU = "/category/huanqiu"                     //环球
    public val SHEYING = "/category/sheying"                     //摄影
    public val WANYI = "/category/wanyi"                         //玩艺
    public val CHAHUA = "/category/chahua"                       //插画
}
