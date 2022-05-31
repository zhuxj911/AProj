package com.xazhuxj.aproj.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.xazhuxj.aproj.ui.viewmodel.TaskViewModal

@Composable
fun CircleRing(boxWidthDp: Int, vm: TaskViewModal) {
    val strokeWidth = 30f
    Canvas(modifier = Modifier.size(boxWidthDp.dp)) {
        //两个方案
        //1.使用startAngle=-10f 和sweepAngle = 200f 并使用rotate（180f）进行翻转
//        rotate(180f) {
//            drawArc(
//                Color(0, 0, 0, 33),
//                startAngle = -10f, sweepAngle = 200f,
//                useCenter = false, style = Stroke(30f, cap = StrokeCap.Round)
//            )
//        }

        //2.使用startAngle=160f 和sweepAngle = 220f
        drawArc(
            Color(0, 0, 0, 15),
            startAngle = 160f, sweepAngle = 220f,
            useCenter = false, style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )

        drawArc(
            Color.White,
            startAngle = 160f,
            sweepAngle = vm.pointOfYearPercent,
            useCenter = false, style = Stroke(strokeWidth, cap = StrokeCap.Round)
        )
    }
}
