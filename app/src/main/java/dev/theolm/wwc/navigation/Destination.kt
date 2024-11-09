package dev.theolm.wwc.navigation

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

@Serializable
object HistoryRoute : Destination
