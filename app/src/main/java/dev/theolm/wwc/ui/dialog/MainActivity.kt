package dev.theolm.wwc.ui.dialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.ext.checkIfWpBusinessIsInstalled
import dev.theolm.wwc.ext.checkIfWpIsInstalled
import dev.theolm.wwc.ext.getPhoneNumberFromIntent
import dev.theolm.wwc.ext.startWhatsAppChat
import dev.theolm.wwc.ui.dialog.error.ErrorDialog
import dev.theolm.wwc.ui.dialog.phoneinput.PhoneInputDialog
import dev.theolm.wwc.ui.theme.AppTheme
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
                if (checkIfWpIsInstalled() || checkIfWpBusinessIsInstalled()) {
                    PhoneInputDialog(
                        initialNumber = intent.getPhoneNumberFromIntent(),
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
