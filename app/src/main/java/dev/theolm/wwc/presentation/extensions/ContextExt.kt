package dev.theolm.wwc.presentation.extensions

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import dev.theolm.wwc.util.WhatsAppPackages

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
