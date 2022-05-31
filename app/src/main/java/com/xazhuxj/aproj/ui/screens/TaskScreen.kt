package com.xazhuxj.aproj.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xazhuxj.aproj.ui.components.appBarHeight
import com.xazhuxj.aproj.ui.theme.AProjTheme
import com.xazhuxj.aproj.ui.viewmodel.TaskViewModal

@Composable
fun TaskScreen(taskVM: TaskViewModal = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF149EE7), Color.White)
                )
            )
    ) {
//        TopAppBar(title = {
//            Text(
//                text="学习任务",
//                modifier= Modifier
//                    .fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                color = Color.White,
//            )
//        }, elevation = 0.dp, modifier = Modifier.statusBarsPadding())


        //标题栏
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .height(appBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "学习任务",
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 18.sp
            )
        }

        LazyColumn() {
            //学习周期
            item {
                Text(
                    text = taskVM.taskDate,
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MapPagePreview() {
    AProjTheme {
        TaskScreen()
    }
}