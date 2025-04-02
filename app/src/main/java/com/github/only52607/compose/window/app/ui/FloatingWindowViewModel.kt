package com.github.only52607.compose.window.app.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FloatingWindowViewModel: ViewModel() {
    private var _dialogVisible by mutableStateOf(false)
    val dialogVisible: Boolean get() = _dialogVisible

    fun showDialog() {
        _dialogVisible = true
    }

    fun dismissDialog() {
        _dialogVisible = false
    }
}