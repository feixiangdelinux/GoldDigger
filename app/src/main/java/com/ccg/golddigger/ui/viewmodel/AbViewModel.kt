package com.ccg.golddigger.ui.viewmodel

import android.app.Application
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.ccg.golddigger.utils.getRetrofit
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2023/12/18 下午4:15
 */
class AbViewModel(application: Application) : BaseViewModelC(application) {
    private val uiListData by lazy { mutableStateListOf<String>() }
    private var vn = 0
    private val isSubmit by lazy { mutableStateOf(false) }
    private val idStr by lazy { mutableStateOf("") }
    fun initData(intent: Intent) {


        intent.getIntExtra("tag", 0).let {
            tag = it
        }
        when (tag) {
            1 -> {
                //获取补全协议信息页面数据
                getCompletionAgreementData(intent)
            }
            else -> {
            }
        }
    }

    /**
     * 获取补全协议信息页面数据
     *
     */
    private fun getCompletionAgreementData(intent: Intent) {
        loadingState.value = false

    }

    /**
     *
     */
    fun postUserAgreementForNet(phone: String, password: String, code: String) {
//        val map = mutableMapOf<String, String>()
//        map["phone".hashCode().toString()] = phone
//        map["password".hashCode().toString()] = password
//        map["code".hashCode().toString()] = code
//        Timber.e(map.toString())

        val map = mutableMapOf<String, String>()
        map["phone"] = phone
        map["password"] = password
        map["code"] = code
        getRetrofit().postRegisterInfo(url = "http://192.168.200.52:8080/XHRServer/RegisterServlet",map=map).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.e("aa", "1  " + response.body()?.string())
                //http://127.0.0.1:8080/XHRServer/AServlet
            }

            override fun onFailure(p0: Call<ResponseBody>, p1: Throwable) {
                Log.e("aa", "" + p1.message)
            }
        })
    }
}