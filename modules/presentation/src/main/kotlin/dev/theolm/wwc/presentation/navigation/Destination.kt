package dev.theolm.wwc.presentation.navigation

import kotlinx.serialization.Serializable

interface Destination

@Serializable
object CountryCodeRoute : Destination

@Serializable
object DefaultAppRoute : Destination

@Serializable
object SettingsHomeRoute : Destination

@Serializable
object AboutRoute : Destination
