package dev.theolm.wwc.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val selectedCountryCode: Country? = null,
    val defaultApp: DefaultApp = DefaultApp.WhatsApp
)
