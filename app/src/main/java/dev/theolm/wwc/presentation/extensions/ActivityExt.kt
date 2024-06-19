package dev.theolm.wwc.presentation.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri

private const val WhatsappUri = "https://api.whatsapp.com/send?phone="

fun Activity.startWhatsAppChat(phone: String, packageId: String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(WhatsappUri + phone)
        setPackage(packageId)
    }.also {
        startActivity(it)
        finish()
    }
}
