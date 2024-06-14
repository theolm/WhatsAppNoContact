package dev.theolm.wwc.presentation.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri

private const val WhatsappUri = "https://api.whatsapp.com/send?phone="

fun Activity.startWhatsAppChat(phone: String) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(WhatsappUri + phone)
    }.also {
        startActivity(it)
        finish()
    }
}
