package com.ccg.golddigger.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ccg.golddigger.network.NetService
import com.ccg.golddigger.ui.theme.GoldDiggerTheme
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author : C4_雍和
 * 描述 :AcActivity
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/20 11:33
 */
class AbActivity : ComponentActivity() {
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getData()

        setContent {
            GoldDiggerTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LoadAbUI()
                }
            }
        }
    }

    /**
     *
     */
    fun getData() {
        val map = mutableMapOf<String, String>()
        map["lotteryCode"] = "1018"
        map["dataNum"] = "9999"
        val retrofit = Retrofit.Builder().baseUrl("http://192.168.200.52:8080").addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(NetService::class.java)
        val repos: Call<ResponseBody> = service.getXHRServer("http://192.168.200.52:8080/XHRServer/AServlet")
        repos.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.e("aa", "1  " + response.body()?.string())
            //http://127.0.0.1:8080/XHRServer/AServlet
            }

            override fun onFailure(p0: Call<ResponseBody>, p1: Throwable) {
                Log.e("aa", "" + p1.message)
            }

        })
    }

    @Composable
    fun LoadAbUI() {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(12.dp))

        }
    }

    /**
     * 计算正确率
     */
    fun getAccuracy() {

    }


}
