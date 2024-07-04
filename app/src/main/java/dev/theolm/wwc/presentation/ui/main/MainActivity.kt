package dev.theolm.wwc.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.theolm.wwc.dialog.error.ErrorDialog
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.presentation.extensions.checkIfWpIsInstalled
import dev.theolm.wwc.presentation.extensions.startWhatsAppChat
import dev.theolm.wwc.presentation.theme.AppTheme
import dev.theolm.wwc.presentation.ui.main.dialog.input.PhoneInputDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), CoroutineScope by MainScope() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme(
                isDynamicColorEnabled = true
            ) {
                if (checkIfWpIsInstalled()) {
                    PhoneInputDialog(
                        onDismiss = {
                            finish()
                        },
                        onStart = { phoneNumber, defaultApp ->
                            onStartChatClicked(phoneNumber, defaultApp)
                        }
                    )
                } else {
                    ErrorDialog(onDismiss = { finish() })
                }
            }
        }
    }

    private fun onStartChatClicked(input: String, app: DefaultApp) = launch {
        startWhatsAppChat(input, app.bundleId)
    }
}
