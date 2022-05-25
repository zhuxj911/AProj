package com.xazhuxj.aproj.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.xazhuxj.aproj.ui.theme.AProjTheme

@Composable
fun MapPage() {
    Text("Map Page")
}

@Preview(showBackground = true)
@Composable
fun MapPagePreview() {
    AProjTheme {
        MapPage()
    }
}