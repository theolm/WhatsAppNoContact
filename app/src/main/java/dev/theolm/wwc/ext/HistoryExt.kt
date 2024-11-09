package dev.theolm.wwc.ext

import dev.theolm.wwc.domain.models.History
import java.text.SimpleDateFormat
import java.util.Locale

fun History.getDate(): String {
    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault())
    return simpleDateFormat.format(this.timestamp)
}
