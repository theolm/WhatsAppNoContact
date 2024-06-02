package dev.theolm.wwc.ui.main.settings.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
object CountryCodeRoute : Destination

@Serializable
object SettingsHomeRoute : Destination
