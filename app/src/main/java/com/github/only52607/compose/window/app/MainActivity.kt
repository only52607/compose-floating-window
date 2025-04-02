package com.github.only52607.compose.window.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.only52607.compose.window.ComposeFloatingWindow
import com.github.only52607.compose.window.app.ui.DialogPermission
import com.github.only52607.compose.window.app.ui.FloatingWindowContent
import com.github.only52607.compose.window.app.ui.theme.ComposeFloatingWindowTheme

class MainActivity : ComponentActivity() {

    private val floatingWindow by lazy {
        createFloatingWindow()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeFloatingWindowTheme {
                val showDialogPermission = remember { mutableStateOf(false) }

                val showing by floatingWindow.showing.collectAsStateWithLifecycle()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                if (floatingWindow.isAvailable()) {
                                    show()
                                } else {
                                    showDialogPermission.value = true
                                }
                            },
                            enabled = !showing
                        ) {
                            Text("Show")
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(
                            onClick = {
                                hide()
                            },
                            enabled = showing
                        ) {
                            Text("Hide")
                        }
                    }
                    DialogPermission(showDialogState = showDialogPermission)
                }
            }
        }
    }

    private fun createFloatingWindow(): ComposeFloatingWindow =
        ComposeFloatingWindow(applicationContext).apply {
            setContent {
                FloatingWindowContent()
            }
        }

    private fun show() {
        floatingWindow.show()
    }

    private fun hide() {
        floatingWindow.hide()
    }
}