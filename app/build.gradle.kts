plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
}

android {
    namespace = ConfigData.applicationBundle
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.applicationBundle
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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

}