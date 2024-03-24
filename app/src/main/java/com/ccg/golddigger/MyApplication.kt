package com.ccg.golddigger

import android.app.Application
import timber.log.Timber.*
import timber.log.Timber.Forest.plant


/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/21 13:35
 */
class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        plant(DebugTree())
    }
}