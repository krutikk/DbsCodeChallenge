package com.dbs.challenge.core.utils

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun Long.getFormattedDate(): String {
    val pattern = "yyyy-MM-dd"
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleDateFormat.format(Date(this * 1000))

}

fun Fragment.showDialog() {
    AlertDialog.Builder(context)
        .setTitle("Error")
        .setMessage("Please try again.")
        .setPositiveButton(
            android.R.string.yes
        ) { dialog, which ->
            dialog.dismiss()
        }
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show()
}