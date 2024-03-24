package com.ccg.golddigger.utils

import android.app.Activity
import android.content.Intent
import com.ccg.golddigger.ui.AaActivity
import com.ccg.golddigger.ui.AbActivity

/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/20 10:14
 */
object NavigationUtils {
    /**
     * 跳转到算法1界面
     * @param context Activity
     */
    fun goAlgorithmOneActivity(context: Activity) {
        context.startActivity(Intent(context, AaActivity::class.java))
    }

    /**
     * 跳转到算法2界面
     * @param context Activity
     */
    fun goAlgorithmTwoActivity(context: Activity) {
        context.startActivity(Intent(context, AbActivity::class.java))
    }
}