package dev.theolm.wwc.models

import androidx.annotation.StringRes
import dev.theolm.wwc.domain.models.CountryCode
import dev.theolm.wwc.ui.settings.defaultcode.CountryCodes

data class Country(
    @StringRes val name: Int,
    val code: String,
)

fun CountryCode.toCountry(): Country? {
    return CountryCodes.codes.firstOrNull {
        it.code == this.code
    }
}