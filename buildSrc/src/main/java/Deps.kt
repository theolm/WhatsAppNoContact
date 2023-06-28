object Deps {
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.androidxCore}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.androidxAppCompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.androidMaterial}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.androidxActivity}" }
    val lifecycleRuntimeCompose by lazy { "androidx.lifecycle:lifecycle-runtime-compose:${Versions.androidxLifecycle}" }
    val viewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.androidxLifecycle}" }
    val accompanistNavigationAnimation by lazy { "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}" }
    val accompanistPermissions by lazy { "com.google.accompanist:accompanist-permissions:${Versions.accompanist}" }
    val accompanistWebview by lazy { "com.google.accompanist:accompanist-webview:${Versions.accompanist}" }
    val accompanistSystemUiController by lazy { "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}" }
    val composeBom by lazy { "androidx.compose:compose-bom:${Versions.androidxComposeBom}" }
    val composeFoundation by lazy { "androidx.compose.foundation:foundation" }
    val composeMaterialIconsExtended by lazy { "androidx.compose.material:material-icons-extended" }
    val composeAnimationGraphics by lazy { "androidx.compose.animation:animation-graphics" }
    val composeUi by lazy { "androidx.compose.ui:ui" }
    val composeMaterial by lazy { "androidx.compose.material:material" }
    val composeMaterial3 by lazy { "androidx.compose.material3:material3:${Versions.androidxComposeMaterial3}" }
    val composeWindowSizeClass by lazy { "androidx.compose.material3:material3-window-size-class:${Versions.androidxComposeMaterial3}" }
    val composeRuntime by lazy { "androidx.compose.runtime:runtime" }
    val composeUiTest by lazy { "androidx.compose.ui:ui-test-junit4" }
    val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling" }
    val composeUiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview" }
    val detektFormatting by lazy { "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detektPlugin}" }
    val detektComposeRules by lazy { "ru.kode:detekt-rules-compose:${Versions.detektComposeRule}" }
}
