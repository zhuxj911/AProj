package com.xazhuxj.aproj.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.insets.ProvideWindowInsets
import com.xazhuxj.aproj.model.entity.NavigationItem

@Composable
fun MainFrame() {

    val navigationItems = listOf(
        NavigationItem(title = "学习", icon = Icons.Filled.Home),
        NavigationItem(title = "图", icon = Icons.Filled.Place),
        NavigationItem(title = "任务", icon = Icons.Filled.Share),
    )

    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }

    ProvideWindowInsets{


        Scaffold(bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier.navigationBarsPadding()
            ) {
                navigationItems.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        selected = currentNavigationIndex == index,
                        onClick = {
                            currentNavigationIndex = index
                        },
                        icon = {
                            Icon(imageVector = navigationItem.icon, contentDescription = null)
                        },
                        label = {
                            Text(text = navigationItem.title)
                        },
                        selectedContentColor = Color(0xFF149EE7),
                        unselectedContentColor = Color(0xFF999999)
                    )
                }
            }
        }) {
            Box(modifier = Modifier.padding(it)){
                //        Text(text="current navigation item: $currentNavigationIndex")
                when (currentNavigationIndex) {
                    0 -> StudyScreen()
                    1 -> FigurePage()
                    2 -> TaskScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}