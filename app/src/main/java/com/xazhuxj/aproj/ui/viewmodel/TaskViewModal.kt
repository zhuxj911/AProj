package com.xazhuxj.aproj.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModal : ViewModel() {
    var taskDate by mutableStateOf("学习周期：2022.01.01-2022.12.31")
        private set
}