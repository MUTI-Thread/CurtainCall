package com.newlife.jy.curtaincall.application

import android.app.Application
import com.newlife.jy.curtaincall.http.ApiClient

/**
 * @author JY
 * 2017/12/12 0:19
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ApiClient.instance.init()
    }
}