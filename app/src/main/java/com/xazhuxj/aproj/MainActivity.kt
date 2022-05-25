package com.xazhuxj.aproj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.xazhuxj.aproj.ui.screens.MainFrame
import com.xazhuxj.aproj.ui.theme.AProjTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //获取系统标题栏高度
//        var statusBarHeight = 0
//        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
//        if(resourceId > 0){
//            statusBarHeight = resources.getDimensionPixelSize(resourceId)
//        }

        //让内容，显示在状态栏和系统导航栏后面：状态栏和导航栏会遮盖部分内容， 不起作用
        //WindowCompat.setDecorFitsSystemWindows(window, false)

        //处理不同机型，导航栏遮盖内容问题，通过底层进行设置了，第2种方案， 但这种方法已经过期
        //window.statusBarColor = Color.Transparent.value.toInt()
        //window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE


        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            AProjTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainFrame()
                }
            }
        }
    }
}