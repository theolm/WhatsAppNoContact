package dev.theolm.wwc.ui.settings

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.theolm.wwc.ext.startWhatsAppChat
import dev.theolm.wwc.navigation.AboutRoute
import dev.theolm.wwc.navigation.CountryCodeRoute
import dev.theolm.wwc.navigation.DefaultAppRoute
import dev.theolm.wwc.navigation.HistoryRoute
import dev.theolm.wwc.navigation.SettingsHomeRoute
import dev.theolm.wwc.ui.settings.about.AboutPage
import dev.theolm.wwc.ui.settings.defaultapp.DefaultAppPage
import dev.theolm.wwc.ui.settings.defaultcode.DefaultCodePage
import dev.theolm.wwc.ui.settings.history.HistoryPage
import dev.theolm.wwc.ui.settings.home.SettingsHomePage
import dev.theolm.wwc.ui.theme.AppTheme

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startOnCountryCode = intent.getBooleanExtra(START_ON_COUNTRY_CODE, false)
        val startDestination = if (startOnCountryCode) CountryCodeRoute else SettingsHomeRoute

        enableEdgeToEdge()
        setContent {
            AppTheme(
                isDynamicColorEnabled = true
            ) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = startDestination) {
                    composable<SettingsHomeRoute> {
                        SettingsHomePage(
                            onCountryCodeClick = {
                                navController.navigate(CountryCodeRoute)
                            },
                            onDefaultAppClick = {
                                navController.navigate(DefaultAppRoute)
                            },
                            onAboutClick = {
                                navController.navigate(AboutRoute)
                            },
                            onHistoryClick = {
                                navController.navigate(HistoryRoute)
                            },
                            onBackPress = {
                                navController.popOrCloseActivity(this@SettingsActivity)
                            },
                        )
                    }
                    composable<CountryCodeRoute> {
                        DefaultCodePage(
                            onBackPress = {
                                navController.popOrCloseActivity(this@SettingsActivity)
                            }
                        )
                    }
                    composable<DefaultAppRoute> {
                        DefaultAppPage(
                            onBackPress = {
                                navController.popOrCloseActivity(this@SettingsActivity)
                            }
                        )
                    }
                    composable<AboutRoute> {
                        AboutPage(
                            onBackPress = {
                                navController.popOrCloseActivity(this@SettingsActivity)
                            }
                        )
                    }
                    composable<HistoryRoute> {
                        HistoryPage(
                            onBackPress = {
                                navController.popOrCloseActivity(this@SettingsActivity)
                            },
                            onItemClick = { number, app ->
                                this@SettingsActivity.startWhatsAppChat(number, app.bundleId)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun NavHostController.popOrCloseActivity(activity: Activity) {
        if (!popBackStack()) {
            activity.finish()
        }
    }

    companion object {
        private const val START_ON_COUNTRY_CODE = "startOnCountryCode"
        fun start(context: Context) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }

        fun startOnCountryCode(context: Context) {
            val intent = Intent(context, SettingsActivity::class.java)
            intent.putExtra(START_ON_COUNTRY_CODE, true)
            context.startActivity(intent)
        }
    }
}
