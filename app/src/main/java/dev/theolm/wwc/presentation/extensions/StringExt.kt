package dev.theolm.wwc.presentation.extensions

/**
 * It only keeps the numbers and the plus sign.
 * @return the string without invalid characters
 */
fun String.removeInvalidCharacters(): String = replace("[^+\\d]".toRegex(), "")
