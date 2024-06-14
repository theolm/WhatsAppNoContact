package dev.theolm.wwc.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val name: String,
    val code: String
)
