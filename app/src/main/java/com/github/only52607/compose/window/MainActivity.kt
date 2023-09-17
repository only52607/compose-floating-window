package com.github.only52607.compose.window

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SystemAlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.only52607.compose.window.ui.FloatingWindowContent
import com.github.only52607.compose.window.ui.theme.ComposeFloatingWindowTheme

class MainActivity : ComponentActivity() {
    private lateinit var floatingWindow: ComposeFloatingWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFloatingWindowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            show()
                        }) {
                            Text("Show")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = {
                            hide()
                        }) {
                            Text("Hide")
                        }
                    }
                }
            }
        }
    }

    private fun createFloatingWindow() {
        floatingWindow = ComposeFloatingWindow(applicationContext)
        floatingWindow.setContent {
            FloatingWindowContent()
        }
    }

    private fun show() {
        if (!::floatingWindow.isInitialized) {
            createFloatingWindow()
        }
        floatingWindow.show()
    }

    private fun hide() {
        if (!::floatingWindow.isInitialized) return
        floatingWindow.hide()
    }
}