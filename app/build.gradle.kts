import config.ConfigData

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.compose.compiler)
    id("detekt-setup")
}

android {
    namespace = ConfigData.applicationBundle
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.applicationBundle
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = 29
        versionName = "v0.20.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        dependenciesInfo {
            // Disables dependency metadata when building APKs.
            includeInApk = false
            // Disables dependency metadata when building Android App Bundles.
            includeInBundle = false
        }
    }

    signingConfigs {
        create("release") {
            val finalPath = "$rootDir/keystore/keystore.jks"
            storeFile = file(finalPath)
            storePassword = System.getenv("SIGNING_STORE_PASSWORD")
            keyAlias = System.getenv("SIGNING_KEY_ALIAS")
            keyPassword = System.getenv("SIGNING_KEY_PASSWORD")
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
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

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeCompiler {
        enableStrongSkippingMode = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs.useLegacyPackaging = true
    }
}

dependencies {
    implementation(projects.modules.resources)
    implementation(projects.modules.domain)
    implementation(projects.modules.data)
    implementation(projects.modules.core)

    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.appCompat)
    implementation(libs.androidx.protoDataStore)
    implementation(libs.androidx.activityCompose)
    implementation(libs.androidx.viewModelCompose)

    implementation(libs.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.materialIconsExtended)
    implementation(libs.compose.animationGraphics)
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.windowSizeClass)
    implementation(libs.compose.runtime)
    implementation(libs.compose.uiToolingPreview)
    implementation(libs.compose.navigation)

    implementation(libs.kotlin.serialization)
    implementation(libs.koin.core)
    implementation(libs.koin.compose)

    implementation(libs.kermit)

    testImplementation(libs.junit.junit)

    debugImplementation(libs.compose.uiTooling)
}
