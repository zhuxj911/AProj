package com.xazhuxj.aproj.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xazhuxj.aproj.model.APoint
import com.xazhuxj.aproj.model.SampleData
import com.xazhuxj.aproj.ui.components.TopAppBar
import com.xazhuxj.aproj.ui.theme.AProjTheme

@Composable
fun PointPage() {
    Column(modifier = Modifier){
        TopAppBar() {
//            Text("测量点")
            Surface(modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                    color = Color(0x33FFFFFF)
                )
            {
                Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                    ) {
                    Icon(imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                    )
                    Text(text = "根据点名搜索测量点", color=Color.White, fontSize =12.sp)
                }
            }
        }
        ListPoints(SampleData.points)
    }
}

@Preview(showBackground = true)
@Composable
fun PointPagePreview() {
    AProjTheme {
        PointPage()
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