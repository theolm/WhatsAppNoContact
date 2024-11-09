package dev.theolm.wwc.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class History(
    val id: Int,
    val number: String,
    val timestamp: Long,
)
