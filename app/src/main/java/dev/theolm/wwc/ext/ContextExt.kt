package dev.theolm.wwc.ext

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import co.touchlab.kermit.Logger
import dev.theolm.wwc.BuildConfig
import dev.theolm.wwc.core.WhatsAppPackages

fun Context.checkIfWpIsInstalled() =
    runCatching {
        if (BuildConfig.DEBUG) return@runCatching true
        val flags = PackageManager.GET_ACTIVITIES
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(
                WhatsAppPackages.Whatsapp,
                PackageManager.PackageInfoFlags.of(flags.toLong())
            )
        } else {
            packageManager.getPackageInfo(WhatsAppPackages.Whatsapp, flags)
        }
        Logger.d { "WhatsApp is installed" }
        true
    }.getOrElse {
        Logger.d { "WhatsApp is NOT installed" }
        false
    }

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
        Logger.d { "WhatsApp Business is installed" }
        true
    }.getOrElse {
        Logger.d { "WhatsApp Business is NOT installed" }
        false
    }

fun Context.openBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}

fun Context.getVersionName(): String = runCatching {
    getPackageInfo().versionName
}.getOrNull().orEmpty().also {
    Logger.d { "Version name: $it" }
}

private fun Context.getPackageInfo(): PackageInfo {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
    } else {
        packageManager.getPackageInfo(packageName, 0)
    }.also {
        Logger.d { "Package info: $it" }
    }
}
