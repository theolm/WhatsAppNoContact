package com.theolm.wwc.ui.main

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.theolm.wwc.ui.main.dialog.ErrorDialog
import com.theolm.wwc.ui.main.dialog.MainDialog
import com.theolm.wwc.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                if (checkIfWpIsInstalled()) {
                    MainDialog(
                        onDismiss = {
                            finish()
                        },
                        onStart = {
                            startChat(it)
                        }
                    )
                } else {
                    ErrorDialog(onDismiss = { finish() })
                }
            }
        }
    }

    private fun startChat(phone: String) {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(whatsappUri + phone)
        }.also {
            startActivity(it)
            finish()
        }
    }

    private fun checkIfWpIsInstalled() =
        runCatching {
            val flags = PackageManager.GET_ACTIVITIES
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                packageManager.getPackageInfo(
                    whatsappPackage,
                    PackageManager.PackageInfoFlags.of(flags.toLong())
                )
            } else {
                @Suppress("DEPRECATION")
                packageManager.getPackageInfo(whatsappPackage, flags)
            }
            true
        }.getOrElse { false }

    private companion object {
        const val whatsappPackage = "com.whatsapp"
        const val whatsappUri = "https://api.whatsapp.com/send?phone="
    }
}
