package com.ccg.golddigger.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ccg.golddigger.bean.NumberBean
import com.ccg.golddigger.ui.theme.GoldDiggerTheme
import com.ccg.golddigger.utils.TimeUtil
import com.tencent.mmkv.MMKV
import timber.log.Timber

/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/25 11:04
 */
class AdActivity : ComponentActivity() {
    private val context = this
    private val expirationTime = TimeUtil.getExpirationTime()
    val finalList: MutableList<NumberBean> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoldDiggerTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LoadAaUI()
                }
            }
        }
    }



    /**
     *
     * @param mList MutableList<Int>
     * @param tag Int
     */
    fun arrangeOne(mList: MutableList<Int>, tag: Int) {

        if (tag == 1) {
            MMKV.defaultMMKV().decodeString("Ad1")?.forEach {
                mList.add(it.toString().toInt())
            }
        } else if (tag == 2) {
            MMKV.defaultMMKV().decodeString("Ad2")?.forEach {
                mList.add(it.toString().toInt())
            }
        } else if (tag == 3) {
            MMKV.defaultMMKV().decodeString("Ad3")?.forEach {
                mList.add(it.toString().toInt())
            }
        } else if (tag == 4) {
            MMKV.defaultMMKV().decodeString("Ad4")?.forEach {
                mList.add(it.toString().toInt())
            }
        } else if (tag == 5) {
            MMKV.defaultMMKV().decodeString("Ad5")?.forEach {
                mList.add(it.toString().toInt())
            }
        } else if (tag == 6) {
            MMKV.defaultMMKV().decodeString("Ad6")?.forEach {
                mList.add(it.toString().toInt())
            }
        } else if (tag == 7) {
            MMKV.defaultMMKV().decodeString("Ad7")?.forEach {
                mList.add(it.toString().toInt())
            }
        } else if (tag == 8) {
            MMKV.defaultMMKV().decodeString("Ad8")?.forEach {
                mList.add(it.toString().toInt())
            }
        } else if (tag == 9) {
            MMKV.defaultMMKV().decodeString("Ad9")?.forEach {
                mList.add(it.toString().toInt())
            }
        } else {
            MMKV.defaultMMKV().decodeString("Ad0")?.forEach {
                mList.add(it.toString().toInt())
            }
        }
    }

    @Composable
    fun LoadAaUI() {
        var mStr1 by remember { mutableStateOf("") }
        val mStr2 = remember { mutableStateOf(AnnotatedString.Builder().toAnnotatedString()) }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextField(value = mStr1, onValueChange = {
                        if (it.length <= 5) {
                            mStr1 = it
                        }
                        val mList: MutableList<Int> = ArrayList()
                        if (it.length in 3..5) {
                            val charArray = it.toCharArray()
                            if (mList.isNotEmpty()) {
                                mList.clear()
                            }
                            if (finalList.isNotEmpty()) {
                                finalList.clear()
                            }
                            charArray.forEach {
                                val aaaa = it.toString().toInt()
                                arrangeOne(mList, aaaa)
                            }
                            mList.sort()
                            setTextContent(mStr2, mList)
                        } else {
                            if (mList.isNotEmpty()) {
                                mList.clear()
                            }
                            if (finalList.isNotEmpty()) {
                                finalList.clear()
                            }
                            if (it.length <= 2) {
                                val tempStr1 = AnnotatedString.Builder()
                                mStr2.value = tempStr1.toAnnotatedString()
                            }
                        }
                    }, modifier = Modifier.width(200.dp), placeholder = {
                        Text(text = "请输入三到五位数")
                    }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = { }) {
                        Text(text = "清空")
                    }
                }

            }
            item {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), contentAlignment = Alignment.TopCenter) {
                    Text(text = mStr2.value, modifier = Modifier.wrapContentSize())
                }
                Text(text = "输入对应的值:")
            }


            items(count = 10) { index ->
                var mStrOne by remember { mutableStateOf("") }
                MMKV.defaultMMKV().decodeString("Ad${index}")?.let {
                    mStrOne = it
                }
                Row(modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "${index}对应: ")
                    TextField(value = mStrOne, onValueChange = {
                        mStrOne = it
                        MMKV.defaultMMKV().encode("Ad${index}", it)
                    }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))
                }

            }

        }


    }

    fun setTextContent(mStr2: MutableState<AnnotatedString>, mList: MutableList<Int>) {
        val threeList: MutableList<NumberBean> = ArrayList()
        val fourList: MutableList<NumberBean> = ArrayList()
        val fiveList: MutableList<NumberBean> = ArrayList()
        val sixList: MutableList<NumberBean> = ArrayList()
        val sevenList: MutableList<NumberBean> = ArrayList()
        val eightList: MutableList<NumberBean> = ArrayList()
        mList.forEach { number ->
            var isHave = false
            finalList.forEach {
                if (it.tag == number) {
                    it.num = it.num + 1
                    isHave = true
                }
            }
            if (!isHave) {
                finalList.add(NumberBean(number, 1))
            }
        }
        finalList.forEach {
            if (it.num == 3) {
                threeList.add(0, it)
            } else if (it.num == 4) {
                fourList.add(0, it)
            } else if (it.num == 5) {
                fiveList.add(0, it)
            } else if (it.num == 6) {
                sixList.add(0, it)
            } else if (it.num == 7) {
                sevenList.add(0, it)
            } else if (it.num == 8) {
                eightList.add(0, it)
            }
        }
        if (System.currentTimeMillis() > expirationTime) {
            val tempStr = AnnotatedString.Builder()
            tempStr.append("软件已到期,想要再用请重新购买")
            tempStr.pop()
            mStr2.value = tempStr.toAnnotatedString()
        } else {
            val tempStr = AnnotatedString.Builder()
            if (eightList.isNotEmpty()) {
                eightList.forEach {
                    tempStr.pushStyle(SpanStyle(color = Color.Red, fontSize = 20.sp))
                    tempStr.append("${it.tag}      ${it.num}次\n")
                }
            }
            if (sevenList.isNotEmpty()) {
                sevenList.forEach {
                    tempStr.pushStyle(SpanStyle(color = Color.Red, fontSize = 20.sp))
                    tempStr.append("${it.tag}      ${it.num}次\n")
                }
            }

            if (sixList.isNotEmpty()) {
                sixList.forEach {
                    tempStr.pushStyle(SpanStyle(color = Color.Red, fontSize = 20.sp))
                    tempStr.append("${it.tag}      ${it.num}次\n")
                }
            }
            if (fiveList.isNotEmpty()) {
                fiveList.forEach {
                    tempStr.pushStyle(SpanStyle(color = Color.Red, fontSize = 20.sp))
                    tempStr.append("${it.tag}      ${it.num}次\n")
                }
            }
            if (fourList.isNotEmpty()) {
                fourList.forEach {
                    tempStr.pushStyle(SpanStyle(color = Color.Red, fontSize = 20.sp))
                    tempStr.append("${it.tag}      ${it.num}次\n")
                }
            }

            if (threeList.isNotEmpty()) {
                threeList.forEach {
                    tempStr.pushStyle(SpanStyle(color = Color.Red, fontSize = 20.sp))
                    tempStr.append("${it.tag}      ${it.num}次\n")
                }
            }
            if (threeList.isNotEmpty() || fourList.isNotEmpty() || fiveList.isNotEmpty() || sixList.isNotEmpty() || sevenList.isNotEmpty() || eightList.isNotEmpty()) {
                tempStr.pop()
            }
            mStr2.value = tempStr.toAnnotatedString()
        }
    }
}



