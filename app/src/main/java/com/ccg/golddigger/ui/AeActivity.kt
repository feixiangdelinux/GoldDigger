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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ccg.golddigger.bean.NumberBean
import com.ccg.golddigger.ui.theme.GoldDiggerTheme
import com.tencent.mmkv.MMKV
import timber.log.Timber
import timber.log.Timber.Forest.tag

/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/25 14:58
 */
class AeActivity : ComponentActivity() {
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoldDiggerTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LoadAcUI()
                }
            }
        }
    }

    @Composable
    fun LoadAcUI() {
        var mStr1 by remember { mutableStateOf("") }
        val mStr2 = remember { mutableStateOf(AnnotatedString.Builder().toAnnotatedString()) }
        val mStr4 = remember { mutableStateListOf<NumberBean>() }
        val mStr5 = remember { mutableStateListOf<NumberBean>() }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    TextField(value = mStr1, onValueChange = {
                        if (it.length <= 25) {
                            mStr1 = it
                            val tempStr = AnnotatedString.Builder()
                            var i = 0
                            while (i < it.length) {
                                if (i + 5 <= it.length) {
                                    tempStr.append("${it.substring(i, i + 5)}\n")
                                } else {
                                    tempStr.append("${it.substring(i)}\n")
                                }
                                i += 5
                            }
                            mStr2.value = tempStr.toAnnotatedString()
                        }
                        if (it.length == 25) {
                            val inputList: MutableList<MutableList<Int>> = ArrayList()
                            var i = 0
                            while (i < it.length) {
                                val aa: MutableList<Int> = ArrayList()
                                if (i + 5 <= it.length) {
                                    it.substring(i, i + 5).forEach {
                                        aa.add(it.toString().toInt())
                                    }
                                }
                                i += 5
                                inputList.add(aa)
                            }
                            val inputLaaaist: MutableList<Int> = ArrayList()
                            inputList.forEach { aa ->
                                Timber.e("A: "+sdfsdsd(aa))
                                inputLaaaist.addAll(sdfsdsd(aa))
                            }
                            mStr4.clear()
                            mStr4.addAll(setTextContentTwo(inputLaaaist))

                            val inputListTwo: MutableList<MutableList<Int>> = ArrayList()
                            val aa: MutableList<Int> = ArrayList()
                            it.forEach {
                                aa.add(it.toString().toInt())
                            }
                            for (i1 in 0..4) {
                                val bb: MutableList<Int> = ArrayList()
                                for (i in i1..aa.lastIndex step 5) {
                                    bb.add(aa[i])
                                }
                                inputListTwo.add(bb)
                            }
                            val inputLaaaistTwo: MutableList<Int> = ArrayList()
                            inputListTwo.forEach { cc ->
//                                Timber.e(""+cc)
                                Timber.e("B: "+sdfsdsd(cc))
                                inputLaaaistTwo.addAll(sdfsdsd(cc))
                            }
                            mStr5.clear()
                            mStr5.addAll(setTextContentTwo(inputLaaaistTwo))
                        }
                    }, modifier = Modifier.width(200.dp), placeholder = {
                        Text(text = "请输入25位数")
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
            }
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(), horizontalArrangement = Arrangement.Center) {
                    Column (modifier = Modifier.weight(1f),horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text = "横")
                        mStr4.forEach {
                            Text(text = "" + it.tag + "  " + it.num + "次", color = Color.Red)
                        }
                    }
                    Column (modifier = Modifier.weight(1f),horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text = "竖")
                        mStr5.forEach {
                            Text(text = "" + it.tag + "  " + it.num + "次", color = Color.Red)
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
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


    fun sdfsdsd(aa: MutableList<Int>): MutableList<Int> {
        val mList: MutableList<Int> = ArrayList()
        aa.forEach {
            arrangeOne(mList, it)
        }
        mList.sort()
        return setTextContent(mList)
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

    fun setTextContentTwo(mList: MutableList<Int>): MutableList<NumberBean> {
        val finalList: MutableList<NumberBean> = ArrayList()
        val threeList: MutableList<NumberBean> = ArrayList()
        val fourList: MutableList<NumberBean> = ArrayList()
        val fiveList: MutableList<NumberBean> = ArrayList()
        val sixList: MutableList<NumberBean> = ArrayList()
        val sevenList: MutableList<NumberBean> = ArrayList()
        val eightList: MutableList<NumberBean> = ArrayList()
        val aaList: MutableList<NumberBean> = ArrayList()
        mList.forEach { number ->
            var isHave = false
            finalList.forEach {
                if (it.tag == number) {
                    it.num += 1
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
        if (eightList.isNotEmpty()) {
            eightList.sortWith { o1, o2 -> o2.tag.compareTo(o1.tag) }
            aaList.addAll(eightList)
        }
        if (sevenList.isNotEmpty()) {
            sevenList.sortWith { o1, o2 -> o2.tag.compareTo(o1.tag) }
            aaList.addAll(sevenList)
        }
        if (sixList.isNotEmpty()) {
            sixList.sortWith { o1, o2 -> o2.tag.compareTo(o1.tag) }
            aaList.addAll(sixList)
        }
        if (fiveList.isNotEmpty()) {
            fiveList.sortWith { o1, o2 -> o2.tag.compareTo(o1.tag) }
            aaList.addAll(fiveList)
        }
        if (fourList.isNotEmpty()) {
            fourList.sortWith { o1, o2 -> o2.tag.compareTo(o1.tag) }
            aaList.addAll(fourList)
        }
        if (threeList.isNotEmpty()) {
            threeList.sortWith { o1, o2 -> o2.tag.compareTo(o1.tag) }
            aaList.addAll(threeList)
        }
        return aaList
    }

    fun setTextContent(mList: MutableList<Int>): MutableList<Int> {
        val finalList: MutableList<NumberBean> = ArrayList()
        val bbList: MutableList<Int> = ArrayList()
        mList.forEach { number ->
            var isHave = false
            finalList.forEach {
                if (it.tag == number) {
                    it.num += 1
                    isHave = true
                }
            }
            if (!isHave) {
                finalList.add(NumberBean(number, 1))
            }
        }
        finalList.forEach {
            if (it.num >= 3) {
                bbList.add(0, it.tag)
            }
        }
        return bbList
    }


}


