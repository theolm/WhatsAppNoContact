package dev.theolm.wwc.core.codes

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val code: String
)
