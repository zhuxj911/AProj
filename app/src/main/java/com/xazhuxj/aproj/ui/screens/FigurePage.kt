package com.xazhuxj.aproj.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.xazhuxj.aproj.ui.components.TopAppBar
import com.xazhuxj.aproj.ui.theme.AProjTheme

@Composable
fun FigurePage() {
    Column(modifier = Modifier){
        TopAppBar() {
            Text("图页面")
        }
        Text("图页面")
    }
}

@Preview(showBackground = true)
@Composable
fun FigurePagePreview() {
    AProjTheme {
        FigurePage()
    }
}