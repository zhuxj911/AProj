package com.xazhuxj.aproj.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.xazhuxj.aproj.model.Category

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

}