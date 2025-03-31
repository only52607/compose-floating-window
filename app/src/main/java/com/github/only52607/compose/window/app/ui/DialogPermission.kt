package com.github.only52607.compose.window.app.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.github.only52607.compose.window.app.R

@Composable
fun DialogPermission(
    showDialogState: MutableState<Boolean> = mutableStateOf(false),
    permission: String = Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
    onDismiss: () -> Unit = { }
) {
    var showDialogPermission by remember { showDialogState }
    val context = LocalContext.current
    if(showDialogPermission.not()) return
    AlertDialog(
        icon = {
            Icon(Icons.Default.Warning, contentDescription = stringResource(R.string.permission_required))
        },
        title = {
            Text(text = stringResource(id = R.string.permission_required))
        },
        text = {
            Text(text = stringResource(R.string.message_permission_to_draw_on_top_others_apps))
        },
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    showDialogPermission = false
                    context.requestPermission(permission)
                }
            ) {
                Text(stringResource(R.string.grant_permission))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    showDialogPermission = false
                }
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    )
}

private fun Context.requestPermission(permission: String) {
    startActivity(
        Intent(
            permission,
        Uri.parse("package:$packageName")
    ).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    })
}