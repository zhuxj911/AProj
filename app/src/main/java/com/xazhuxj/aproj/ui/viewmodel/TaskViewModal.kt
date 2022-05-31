package com.xazhuxj.aproj.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModal : ViewModel() {
    var taskDate by mutableStateOf("学习周期：2022.01.01-2022.12.31")
        private set

    //学年总积分
    var totalPointOfYear = 13500f
        private set

    //学年积分
    var pointOfYear by mutableStateOf(10000)
        private set

    //学年积分进度 = 220f * pointOfYear / 学年总积分
    //
    var pointOfYearPercent by mutableStateOf(0f)
        private set

    /**
     *  更新积分进度
     * @return Float
     */
    fun updatePointPercent() {
        pointOfYearPercent = 220f * pointOfYear / totalPointOfYear
    }

    //一周积分情况
    var pointsOfWeek by mutableStateOf(listOf(0.0, 2.0, 6.0, 9.5, 10.0, 15.0, 5.0))
        private set

    var weeks = listOf("02.05", "02.06", "02.07", "02.08", "02.09", "02.10", "今日")
        private set

    //今日积分
    private var todayPoint = 10

    //今日提醒文字
    var tips by mutableStateOf("今日获得0积分，快去完成下面任务吧")
        private set

    fun updateTips(){
        tips = when (todayPoint) {
            0 -> {
                "今日获得0积分，快去完成下面任务吧"
            }
            in 1 .. 14 -> {
                "今日获得${todayPoint}积分"
            }
            else -> {
                "今日获得${todayPoint}积分, 已经完成任务"
            }
        }
    }
}