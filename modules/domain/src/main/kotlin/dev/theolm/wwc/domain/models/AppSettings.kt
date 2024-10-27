package dev.theolm.wwc.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val selectedCountryCode: CountryCode? = null,
    val defaultApp: DefaultApp = DefaultApp.WhatsApp
)
