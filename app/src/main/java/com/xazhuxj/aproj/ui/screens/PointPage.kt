package com.xazhuxj.aproj.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.xazhuxj.aproj.model.APoint
import com.xazhuxj.aproj.ui.components.*
import com.xazhuxj.aproj.ui.components.TopAppBar
import com.xazhuxj.aproj.ui.theme.AProjTheme
import com.xazhuxj.aproj.ui.viewmodel.ArticleViewModel
import com.xazhuxj.aproj.ui.viewmodel.MainViewModel
import com.xazhuxj.aproj.ui.viewmodel.VideoViewModel


@Composable
fun StudyScreen(vm: MainViewModel = viewModel(),
                vmArticle: ArticleViewModel = viewModel(),
                videoViewModel: VideoViewModel= viewModel()
) {
    Column(modifier = Modifier) {
        TopAppBar(modifier = Modifier.padding(horizontal = 8.dp)){

           //中间分割加Spacer 是一种方法
            // Spacer(modifier = Modifier.width(8.dp))

//            Text("测量点")
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .weight(1f),
                color = Color(0x33FFFFFF)
            )
            {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "根据点名搜索测量点",
                        color = Color.White,
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            //学习进度
            Text(text = "学习\n进度", fontSize = 10.sp, color = Color.White)

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "26%", fontSize = 12.sp, color = Color.White)

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White
            )
        }

        //分类数据
        TabRow(selectedTabIndex = vm.categoryIndex,
            backgroundColor=Color(0x66149EE7),
            contentColor = Color(0xFF149EE7)
        ) {
            vm.categories.forEachIndexed{index, category ->
                Tab(selected = vm.categoryIndex == index, onClick = {
                    vm.updateCategoryIndex(index)
                },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666)
                ) {
                    Text(
                        text = category.title,
                        modifier = Modifier.padding(vertical = 8 .dp),
                    fontSize = 14.sp)
                }
            }
        }

//        TabRow(selectedTabIndex = vm.currentTypeIndex,
//            backgroundColor=Color.Transparent,
//            contentColor = Color(0xFF149EE7)
//        ){
//            vm.types.forEachIndexed{ index, item ->
//                Tab(selected = vm.currentTypeIndex == index, onClick ={
//                    vm.updateTypeIndex(index)
//                },
//                selectedContentColor = Color(0xFF149EE7),
//                unselectedContentColor = Color(0xFF666666)
//                ){
//                  Text(
//                      text = item,
//                      modifier = Modifier.padding(vertical = 8 .dp),
//                      fontSize = 16.sp)
//              }
//            }
//        }

        TabRow(selectedTabIndex = vm.currentTypeIndex,
            backgroundColor=Color.Transparent,
            contentColor = Color(0xFF149EE7),
            indicator = {},
            divider ={}
        ){
            vm.types.forEachIndexed{ index, dataType ->
                LeadingIconTab(selected = vm.currentTypeIndex == index, onClick ={
                    vm.updateTypeIndex(index)
                },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666),
                    icon = {
                        Icon(imageVector = dataType.icon, contentDescription = null)
                    },
                    text = {
                        Text(
                            text = dataType.title,
                            modifier = Modifier.padding(vertical = 8 .dp),
                            fontSize = 16.sp)
                    }
                )
            }
        }

        //轮播图
//        HorizontalPager(count = vm.swiperData.size,
////            contentPadding = PaddingValues(16.dp)
////        itemSpacing = 16.dp
//        modifier = Modifier.padding(horizontal = 8.dp).
//        clip(RoundedCornerShape(8.dp))
//        ) { index ->
//            AsyncImage(
//                model = vm.swiperData[index].imageUrl,
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(7 / 3f),
//                contentScale = ContentScale.Crop
//            )
//
//        }
//        //将上面注释代码改为组件形式
         //轮播图
//        SwiperContent(vm)
//
//        //通知公告
//        NotificationContent(vm)


       //ListPoints(SampleData.points)

        LazyColumn(){
            //轮播图
            item{ SwiperContent(vm)}

            //通知公告
            item{ NotificationContent(vm)}

            if(vm.showArticleList){
                //文章列表
                items(vmArticle.list){ article ->
                    ArticleItem(article)
                }
            }else{
                //视频列表
                items(videoViewModel.list){videoEntity ->
                    VideoItem(videoEntity)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PointPagePreview() {
    AProjTheme {
        StudyScreen()
    }
}


@Composable
fun ListPoints(points: List<APoint>) {
    LazyColumn {
        items(points) { point ->
            PointCard(point)
        }
    }
}

@Composable
fun PointCard(pnt: APoint) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
//        Image(
//            painter = painterResource(id = R.drawable.profile_picture) ,
//            contentDescription = "Contact profile picture",
//            modifier = Modifier
//                .size(40.dp)
//                .clip(CircleShape)
//                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
//        )

        Spacer(modifier = Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor: Color by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = pnt.name,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.width(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = "x=${pnt.x}, y=${pnt.y}\n"
                            + "B=${pnt.dmsB}, L=${pnt.dmsL}\n"
                            + "γ=${pnt.gamma}, m=${pnt.m}",
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}