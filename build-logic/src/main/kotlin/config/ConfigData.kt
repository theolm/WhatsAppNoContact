package config

import org.gradle.api.JavaVersion

object ConfigData {
    const val applicationBundle = "dev.theolm.wwc"
    const val compileSdkVersion = 36
    const val minSdkVersion = 24
    const val targetSdkVersion = 36
    val javaVersion = JavaVersion.VERSION_17
}
