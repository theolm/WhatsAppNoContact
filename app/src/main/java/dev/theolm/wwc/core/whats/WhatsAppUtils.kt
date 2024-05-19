package dev.theolm.wwc.core.whats

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build

private const val WhatsappPackage = "com.whatsapp"
private const val WhatsappUri = "https://api.whatsapp.com/send?phone="

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

fun Activity.startWhatsAppChat(phone: String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(WhatsappUri + phone)
    }.also {
        startActivity(it)
        finish()
    }
}
