package com.ccg.golddigger.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.RegexUtils
import com.ccg.golddigger.ui.theme.GoldDiggerTheme
import com.ccg.golddigger.ui.viewmodel.AbViewModel
import com.ccg.golddigger.utils.NavigationUtils

/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/21 12:12
 */
class RegisterActivity : ComponentActivity() {
    private val context = this
    private val viewModel by lazy { ViewModelProvider(this)[AbViewModel::class.java] }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.initData(intent)
        setContent {
            GoldDiggerTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    LoadMainUI()
                }
            }
        }
    }

    @Composable
    fun LoadMainUI() {
        var phoneStr by remember { mutableStateOf("") }
        var passwordStr by remember { mutableStateOf("") }
        var codeStr by remember { mutableStateOf("") }
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background), contentAlignment = Alignment.Center) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "账号注册", color = MaterialTheme.colorScheme.onBackground, fontSize = 30.sp)
                Text(text = "在线客服  获取邀请码加 QQ:  798869270", color = Color.DarkGray, fontSize = 12.sp)
                Text(text = "在线客服 获取邀请码加微信:  798869270", color = Color.DarkGray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = phoneStr, onValueChange = {
                    phoneStr = it
                }, placeholder = {
                    Text(text = "请输入手机号", color = Color.DarkGray.copy(0.5f), fontSize = 14.sp)
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone), singleLine = true)
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = passwordStr, onValueChange = {
                    passwordStr = it
                }, placeholder = {
                    Text(text = "请输入密码", color = Color.DarkGray.copy(0.5f), fontSize = 14.sp)
                }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), singleLine = true)
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = codeStr, onValueChange = {
                    codeStr = it
                }, placeholder = {
                    Text(text = "请输入邀请码(必填)", color = Color.DarkGray.copy(0.5f), fontSize = 14.sp)
                }, singleLine = true)
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    if (RegexUtils.isTel(phoneStr)) {
                        Toast.makeText(context, "请输入正确手机号", Toast.LENGTH_SHORT).show()
                    } else if (passwordStr.length > 20) {
                        Toast.makeText(context, "密码长度不能大于20", Toast.LENGTH_SHORT).show()
                    } else if (codeStr.isEmpty()) {
                        Toast.makeText(context, "请输入邀请码", Toast.LENGTH_SHORT).show()
                    } else if (codeStr.length > 20) {
                        Toast.makeText(context, "邀请码长度不能大于20", Toast.LENGTH_SHORT).show()
                    } else {
                        //把用户名密码提交到后台
                        viewModel.postUserAgreementForNet(phoneStr,passwordStr,codeStr)
                    }
                }, modifier = Modifier.width(200.dp)) {
                    Text(text = "注册")
                }
            }
        }
    }
}
