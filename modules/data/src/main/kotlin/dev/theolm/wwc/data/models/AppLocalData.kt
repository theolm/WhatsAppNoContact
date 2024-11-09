package dev.theolm.wwc.data.models

import dev.theolm.wwc.domain.models.CountryCode
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.domain.models.History
import kotlinx.serialization.Serializable

@Serializable
internal data class AppLocalData(
    val version: Int = 2,
    val selectedCountryCode: CountryCode? = null,
    val defaultApp: DefaultApp = DefaultApp.WhatsApp,
    val history: List<History> = emptyList()
)
