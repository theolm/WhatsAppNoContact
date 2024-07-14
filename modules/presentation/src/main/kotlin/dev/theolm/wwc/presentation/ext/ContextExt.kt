package dev.theolm.wwc.presentation.ext

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import dev.theolm.wwc.core.WhatsAppPackages

fun Context.checkIfWpIsInstalled() =
    runCatching {
        val flags = PackageManager.GET_ACTIVITIES
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(
                WhatsAppPackages.Whatsapp,
                PackageManager.PackageInfoFlags.of(flags.toLong())
            )
        } else {
            packageManager.getPackageInfo(WhatsAppPackages.Whatsapp, flags)
        }
        true
    }.getOrElse { false }

fun Context.checkIfWpBusinessIsInstalled() =
    runCatching {
        val flags = PackageManager.GET_ACTIVITIES
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(
                WhatsAppPackages.WhatsappBuisness,
                PackageManager.PackageInfoFlags.of(flags.toLong())
            )
        } else {
            packageManager.getPackageInfo(WhatsAppPackages.WhatsappBuisness, flags)
        }
        true
    }.getOrElse { false }

fun Context.openBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}

fun Context.getVersionName(): String = runCatching {
    getPackageInfo().versionName
}.getOrElse { "" }

@Suppress("DEPRECATION")
private fun Context.getPackageInfo(): PackageInfo {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
    } else {
        packageManager.getPackageInfo(packageName, 0)
    }
}
