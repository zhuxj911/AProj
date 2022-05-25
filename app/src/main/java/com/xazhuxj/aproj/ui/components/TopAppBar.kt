package com.xazhuxj.aproj.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * 统一标题栏
 *
 * @param content 标题内容
 */
@Composable
fun TopAppBar(content: @Composable () -> Unit) {

    //解决颜色不一致的问题
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = Unit){
        systemUiController.setStatusBarColor(Color.Transparent)
    }

    //标题栏高度
    val appBarHeight = 56.dp

    //将系统状态栏高度px 转换 为dp
    val statusBarHeightDp = with(LocalDensity.current){
         LocalWindowInsets.current.statusBars.top.toDp()
    }

    Row(
        modifier = Modifier
            .background(
                Brush.linearGradient(
                    listOf(
                        MaterialTheme.colors.primary,
                        MaterialTheme.colors.secondary
                    )
                )
            )
            .fillMaxWidth()
            .height(appBarHeight + statusBarHeightDp)
            .padding(top = statusBarHeightDp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        content()
    }
}

@Preview(name = "TopAppBar")
@Composable
private fun PreviewTopAppBar() {
    TopAppBar {
        Text(text = "标题栏")
    }
}