package dev.theolm.wwc.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class History(
    val id: String,
    val number: String,
    val timestamp: Long,
)