package com.ccg.golddigger.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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

/**
 * @author : C4_雍和
 * 描述 :AbActivity
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/20 10:16
 * RegisterActivity
 */
class AaActivity : ComponentActivity() {
    private val context = this
    private val expirationTime = TimeUtil.getExpirationTime()
    val mList: MutableList<Int> = ArrayList()
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

    @Composable
    fun LoadAaUI() {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            var mStr1 by remember { mutableStateOf("") }
            var mStr2 = remember { mutableStateOf(AnnotatedString.Builder().toAnnotatedString()) }
            Spacer(modifier = Modifier.height(20.dp))
            Row() {
                TextField(value = mStr1, onValueChange = {
                    if (it.length <= 5) {
                        if (it.length >= 3) {
                            Log.e("aa", "几个了  " + it.length)
                        }
                        mStr1 = it
                    }
                    if (it.length > 2 && it.length < 6) {
                        val charArray = it.toCharArray()
                        if (mList.isNotEmpty()) {
                            mList.clear()
                        }
                        if (finalList.isNotEmpty()) {
                            finalList.clear()
                        }
                        charArray.forEach {
                            val aaaa = it.toString().toInt()
                            if (aaaa == 1) {
                                mList.add(3)
                                mList.add(4)
                                mList.add(8)
                                mList.add(9)
                            } else if (aaaa == 2) {
                                mList.add(4)
                                mList.add(5)
                                mList.add(9)
                                mList.add(10)
                            } else if (aaaa == 3) {
                                mList.add(1)
                                mList.add(5)
                                mList.add(6)
                                mList.add(10)
                            } else if (aaaa == 4) {
                                mList.add(1)
                                mList.add(2)
                                mList.add(6)
                                mList.add(7)
                            } else if (aaaa == 5) {
                                mList.add(2)
                                mList.add(3)
                                mList.add(7)
                                mList.add(8)
                            } else if (aaaa == 6) {
                                mList.add(3)
                                mList.add(4)
                                mList.add(8)
                                mList.add(9)
                            } else if (aaaa == 7) {
                                mList.add(4)
                                mList.add(5)
                                mList.add(9)
                                mList.add(10)
                            } else if (aaaa == 8) {
                                mList.add(1)
                                mList.add(5)
                                mList.add(6)
                                mList.add(10)
                            } else if (aaaa == 9) {
                                mList.add(1)
                                mList.add(2)
                                mList.add(6)
                                mList.add(7)
                            } else {
                                mList.add(2)
                                mList.add(3)
                                mList.add(7)
                                mList.add(8)
                            }
                        }
                        mList.sort()
                        setTextContent(mStr2)
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
            Text(text = mStr2.value)
        }
    }

    fun setTextContent(mStr2: MutableState<AnnotatedString>) {
        val oneList: MutableList<NumberBean> = ArrayList()
        val twoList: MutableList<NumberBean> = ArrayList()
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
            if (it.num == 1) {
                oneList.add(0, it)
            } else if (it.num == 2) {
                twoList.add(0, it)
            } else if (it.num == 3) {
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



