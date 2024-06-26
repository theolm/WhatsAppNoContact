package dev.theolm.wwc.domain.models

import dev.theolm.wwc.util.WhatsAppPackages
import kotlinx.serialization.Serializable

@Serializable
enum class DefaultApp(val bundleId: String) {
    WhatsApp(WhatsAppPackages.Whatsapp),
    WhatsAppBusiness(WhatsAppPackages.WhatsappBuisness),
}
