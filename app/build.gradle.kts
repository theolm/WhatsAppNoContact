plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.detekt).version(Versions.detektPlugin)
}

android {
    namespace = ConfigData.applicationBundle
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.applicationBundle
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = System.getenv("GITHUB_REF_NAME") ?: ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = ConfigData.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ConfigData.kotlinCompilerExtensionVersion
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs.useLegacyPackaging = true
    }
}

dependencies {
    implementation(project(":color"))

    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.activityCompose)
    implementation(Deps.lifecycleRuntimeCompose)
    implementation(Deps.viewModelCompose)
    implementation(Deps.accompanistNavigationAnimation)
    implementation(Deps.accompanistPermissions)
    implementation(Deps.accompanistWebview)
    implementation(Deps.accompanistSystemUiController)
    implementation(platform(Deps.composeBom))
    implementation(Deps.composeFoundation)
    implementation(Deps.composeMaterialIconsExtended)
    implementation(Deps.composeAnimationGraphics)
    implementation(Deps.composeUi)
    implementation(Deps.composeMaterial)
    implementation(Deps.composeMaterial3)
    implementation(Deps.composeWindowSizeClass)
    implementation(Deps.composeRuntime)

    implementation(Deps.composeUiToolingPreview)
    debugImplementation(Deps.composeUiTooling)

    detektPlugins(Deps.detektFormatting)
    detektPlugins(Deps.detektComposeRules)
}

detekt {
    buildUponDefaultConfig = true
    config.setFrom("$projectDir/config/detekt.yml")
    baseline = file("$projectDir/config/baseline.xml")
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(false)
        txt.required.set(false)
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    jvmTarget = "1.8"
}
tasks.withType<io.gitlab.arturbosch.detekt.DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}
