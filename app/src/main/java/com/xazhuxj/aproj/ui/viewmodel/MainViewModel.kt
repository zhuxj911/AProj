package com.xazhuxj.aproj.ui.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.xazhuxj.aproj.model.Category
import com.xazhuxj.aproj.model.entity.DataType

class MainViewModel : ViewModel() {
    val categories by mutableStateOf(
        listOf(
            Category("GPS点"),
            Category("导线点"),
            Category("三角点"),
            Category("测点"),
        )
    )

    //当前分类下标
    var categoryIndex by mutableStateOf(0)
        private set

    /**
     * 更新分类下标
     *
     * @param index
     */
    fun updateCategoryIndex(index: Int) {
        categoryIndex = index
    }


    //类型数据
    val types by mutableStateOf(
        listOf(
            DataType("相关资讯", Icons.Default.Description),
            DataType("视频课程", Icons.Default.SmartDisplay)
        )
    )

    //当前类型下标
    var currentTypeIndex by mutableStateOf(0)
        private set

    /**
     * 更新类型下标
     *
     * @param index Int
     */
    fun updateTypeIndex(index: Int) {
        currentTypeIndex = index
    }

}