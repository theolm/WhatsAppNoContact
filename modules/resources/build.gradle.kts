import config.ConfigData

plugins {
    id("module-setup")
}

android {
    namespace = ConfigData.applicationBundle
}

