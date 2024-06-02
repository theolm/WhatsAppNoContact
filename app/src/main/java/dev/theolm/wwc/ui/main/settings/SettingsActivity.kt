package dev.theolm.wwc.ui.main.settings

import android.annotation.SuppressLint
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
import dev.theolm.wwc.ui.main.settings.defaultcode.DefaultCodePage
import dev.theolm.wwc.ui.main.settings.home.SettingsHomePage
import dev.theolm.wwc.ui.main.settings.navigation.CountryCodeRoute
import dev.theolm.wwc.ui.main.settings.navigation.SettingsHomeRoute
import dev.theolm.wwc.ui.theme.AppTheme

class SettingsActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
                            onBackPress = {
                                navController.popOrCloseActivity(this@SettingsActivity)
                            }
                        )
                    }
                    composable<CountryCodeRoute> {
                        DefaultCodePage(
                            onBackPress = {
                                navController.popOrCloseActivity(this@SettingsActivity)
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
