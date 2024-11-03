import config.ConfigData

plugins {
    id("module-setup")
}

android {
    namespace = ConfigData.applicationBundle + ".test"
}

dependencies {
    implementation(libs.androidx.protoDataStore)
    implementation(projects.modules.data)
    implementation(projects.modules.domain)
}
