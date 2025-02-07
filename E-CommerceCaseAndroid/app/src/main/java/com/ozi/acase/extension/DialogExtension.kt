package com.ozi.acase.extensions

import android.app.AlertDialog
import android.content.Context

fun Context.showErrorDialog(
    title: String = "Error",
    message: String,
    buttonText: String = "OK",
    onDismiss: (() -> Unit)? = null
) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(buttonText) { dialog, _ ->
            dialog.dismiss()
            onDismiss?.invoke()
        }
        .show()
}