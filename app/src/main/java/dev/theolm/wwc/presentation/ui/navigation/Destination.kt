package dev.theolm.wwc.presentation.ui.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
object CountryCodeRoute : Destination

@Serializable
object DefaultAppRoute : Destination

@Serializable
object SettingsHomeRoute : Destination
