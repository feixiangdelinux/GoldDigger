package com.ccg.golddigger.network

import com.ccg.golddigger.bean.AABean
import io.reactivex.rxjava3.core.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/20 11:38
 */
interface NetService {
//    @GET
//    fun getXxghsData(@Url url: String, @QueryMap map: Map<String, String>): Flowable<XxghsBean>
//@GET
//suspend fun addQQGroupTwo(@Url url: String, @QueryMap map: Map<String, String>): QQGroupBean
//@POST
//fun postDeleteFav(@Url url: String, @Body map: Map<String, String>): Flowable<NoteResBean>
//@POST
//suspend fun postSeizeSixtyListData(@Url url: String, @Body map: Map<String, String>): BaseResponseBean<SeizeSixtyTwoBean.DataBean>
    @POST
    fun getXHRServer(@Url url: String): Call<ResponseBody>
    @POST
    fun postRegisterInfo(@Url url: String, @QueryMap map: Map<String, String>): Call<ResponseBody>


    @POST
    fun postRegisterssInfo(@Url url: String, @Body map: Map<String, String>): Call<ResponseBody>
}