package dev.theolm.wwc.presentation.ext

import android.content.Intent

/**
 * Extracts the shared phone number from the intent.
 * @return the shared phone number or null if it's not a phone number.
 */
fun Intent.getSharedPhoneNumber(): String? {
    return runCatching {
        val clipDataText = this.clipData?.getItemAt(0)?.text ?: return null
        clipDataText.extractPhoneNumber()
    }.getOrNull()
}

internal fun CharSequence.extractPhoneNumber(): String {
    return this.toString()
        .treatForChromeShare()
        .numbersOnly()
}

/**
 * Treats the shared text from Chrome share.
 *
 * Chrome shares the selected text plus the page title and URL.
 * This function returns only the selected text.
 */
private fun String.treatForChromeShare(): String {
    return this.split("\n").firstOrNull().orEmpty()
}

private fun String.numbersOnly(): String {
    return this.replace("[^0-9]".toRegex(), "")
}
