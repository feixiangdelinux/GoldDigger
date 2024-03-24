package com.ccg.golddigger.bean

data class AABean(
    val code: Int,
    val `data`: List<Data>,
    val msg: String
) {
    data class Data(
        val createdTime: String,
        val issue: String,
        val lotteryCode: String,
        val lotteryName: Any,
        val lotteryType: Any,
        val `open`: Boolean,
        val openNumber: String,
        val openTime: String,
        val tenantCode: String
    )
}