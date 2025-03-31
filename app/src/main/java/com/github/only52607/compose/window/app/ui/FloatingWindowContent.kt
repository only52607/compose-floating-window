package com.github.only52607.compose.window.app.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.SystemAlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.only52607.compose.window.LocalFloatingWindow
import com.github.only52607.compose.window.dragFloatingWindow

@Composable
fun FloatingWindowContent(
    model: FloatingWindowViewModel = viewModel()
) {
    val floatingWindow = LocalFloatingWindow.current
    if (model.dialogVisible) {
        SystemAlertDialog(
            onDismissRequest = { model.dismissDialog() },
            confirmButton = {
                TextButton(onClick = { model.dismissDialog() }) {
                    Text(text = "OK")
                }
            },
            text = {
                Text(text = "This is a system dialog")
            }
        )
    }
    FloatingActionButton(
        modifier = Modifier.dragFloatingWindow(),
        onClick = {
            model.showDialog()
        }) {
        Icon(Icons.Filled.Call, "Call")
    }
}