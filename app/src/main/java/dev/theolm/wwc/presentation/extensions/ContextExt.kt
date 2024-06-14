package dev.theolm.wwc.presentation.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build

private const val WhatsappPackage = "com.whatsapp"
fun Context.checkIfWpIsInstalled() =
    runCatching {
        val flags = PackageManager.GET_ACTIVITIES
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(
                WhatsappPackage,
                PackageManager.PackageInfoFlags.of(flags.toLong())
            )
        } else {
            packageManager.getPackageInfo(WhatsappPackage, flags)
        }
        true
    }.getOrElse { false }
