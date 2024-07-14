import config.ConfigData
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.the

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("detekt-setup")
}

android {
    namespace = ConfigData.applicationBundle
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        minSdk = ConfigData.minSdkVersion
    }

    kotlin {
        sourceSets.all {
            languageSettings.enableLanguageFeature("ExplicitBackingFields")
        }
    }

    compileOptions {
        sourceCompatibility = ConfigData.javaVersion
        targetCompatibility = ConfigData.javaVersion
    }

    kotlinOptions {
        jvmTarget = ConfigData.javaVersion.toString()
    }
}

val libs = the<LibrariesForLibs>()
dependencies {
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
}
