package com.ccg.golddigger.utils

import androidx.lifecycle.ViewModel
import com.ccg.golddigger.network.NetService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 *   @author : C4画师
 *     描述  :kt写的工具类
 * 主要功能  :一些常用的工具方法都在这里面
 * 维护人员 : C4画师
 *   date  : 19-7-15上午10:01
 */

fun ViewModel.getRetrofit(): NetService {
    return Retrofit.Builder().baseUrl("http://192.168.200.52:8080/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build().create(NetService::class.java)
}
