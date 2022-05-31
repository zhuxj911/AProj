package com.xazhuxj.aproj.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xazhuxj.aproj.ui.components.ChartView
import com.xazhuxj.aproj.ui.components.CircleRing
import com.xazhuxj.aproj.ui.components.appBarHeight
import com.xazhuxj.aproj.ui.theme.AProjTheme
import com.xazhuxj.aproj.ui.viewmodel.TaskViewModal

@Composable
fun TaskScreen(taskVM: TaskViewModal = viewModel()) {
    //圆环高度
    var boxWidthDp: Int
    with(LocalConfiguration.current) {
        boxWidthDp = screenWidthDp / 2
    }
    //当学员积分改变时没重新计算学年积分
    LaunchedEffect(taskVM.pointOfYear) {
        taskVM.updatePointPercent()
    }

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
                .height(appBarHeight)
                .padding(top = 8.dp),
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

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            //学习周期
            item {
                Text(
                    text = taskVM.taskDate,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            //学习进度
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(boxWidthDp.dp)
                        .padding(top = 8.dp)
                ) {
                    //圆环
                    CircleRing(boxWidthDp, taskVM)


                    //进度数据
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            buildAnnotatedString {
                                append(taskVM.pointOfYear.toString())
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("分")
                                }
                            },
                            fontSize = 36.sp,
                            color = Color.White
                        )

                        Text(
                            text = "学年积分",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp)
                ) {
                    Column() {
                        Text(
                            text = "${taskVM.totalPointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "学年规定积分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                    Column() {
                        Text(
                            text = "${taskVM.totalPointOfYear - taskVM.pointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "还差积分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
            
            //学习明细
            item{
                Column(modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(top = 8.dp)
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                ) {
                    Text(text = "学习明细", fontSize = 16.sp,color=Color(0xFF333333))
                    Text(text = "最近一周获得积分情况", fontSize = 14.sp,color=Color(0xFF999999))

                    //积分情况的折线图
                    ChartView(taskVM.pointsOfWeek, modifier = Modifier.padding(vertical = 8.dp))

                    //下面的这个应该组织到 ChartView 这个里面吧？？？
                    Row() {
                        taskVM.weeks.forEach{
                            Text(text = it, fontSize=12.sp, color=Color(0xFF999999),
                                textAlign=TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }


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