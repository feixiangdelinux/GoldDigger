package com.ccg.golddigger.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ccg.golddigger.ui.theme.GoldDiggerTheme
import com.ccg.golddigger.utils.NavigationUtils

/**
 * AaActivity
 */
class MainActivity : ComponentActivity() {
    private val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                NavigationUtils.goAlgorithmOneActivity(context)
            }) {
                Text(text = "算法1")
            }
            Button(onClick = {
                NavigationUtils.goAlgorithmTwoActivity(context)
            }) {
                Text(text = "算法2")
            }
            Button(onClick = {
                NavigationUtils.goAlgorithmThreeActivity(context)
            }) {
                Text(text = "算法3")
            }
            Button(onClick = {
                NavigationUtils.goAlgorithmFourActivity(context)
            }) {
                Text(text = "算法4")
            }
            Button(onClick = {
                NavigationUtils.goAlgorithmFiveActivity(context)
            }) {
                Text(text = "算法5")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GoldDiggerTheme {
    }
}