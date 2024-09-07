package dev.theolm.wwc.ext

import android.app.Activity
import android.content.Intent
import android.net.Uri

private const val WhatsappUri = "https://api.whatsapp.com/send?phone="

fun Activity.startWhatsAppChat(phone: String, packageId: String) {
    // Only set the intent package if both WhatsApp and WhatsApp Business are installed
    val shouldSetPackage = this.checkIfWpIsInstalled() && this.checkIfWpBusinessIsInstalled()
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(dev.theolm.wwc.ext.WhatsappUri + phone)
        if (shouldSetPackage) {
            setPackage(packageId)
        }
    }.also {
        startActivity(it)
        finish()
    }
}
