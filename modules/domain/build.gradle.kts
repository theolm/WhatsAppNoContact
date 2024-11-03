import config.ConfigData

plugins {
    id("module-setup")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = ConfigData.applicationBundle + ".domain"
}

dependencies {
    implementation(projects.modules.core) //TODO: remove this dependency
    implementation(libs.kotlin.serialization)
    implementation(libs.koin.core)

    testImplementation(libs.bundles.unitTest)
    testImplementation(projects.modules.data)
    testImplementation(projects.modules.test)
}
