package dev.theolm.wwc.ui.main.settings

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.theolm.wwc.ui.main.settings.defaultcode.DefaultCodePage
import dev.theolm.wwc.ui.main.settings.defaultcode.DefaultCodeRoute
import dev.theolm.wwc.ui.main.settings.home.SettingsHomePage
import dev.theolm.wwc.ui.main.settings.home.SettingsHomeRoute
import dev.theolm.wwc.ui.theme.AppTheme

class SettingsActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(
                isDynamicColorEnabled = true
            ) {
                val navController = rememberNavController()

                CompositionLocalProvider(LocalNavController provides navController) {
                    NavHost(navController, startDestination = SettingsHomeRoute) {
                        composable<SettingsHomeRoute> {
                            SettingsHomePage(
                                onBackPress = {
                                    this@SettingsActivity.finish()
                                }
                            )
                        }
                        composable<DefaultCodeRoute> {
                            DefaultCodePage()
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }
    }
}

