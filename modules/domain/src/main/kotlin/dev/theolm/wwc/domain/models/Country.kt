package dev.theolm.wwc.domain.models

import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @StringRes val name: Int,
    val code: String,
)
