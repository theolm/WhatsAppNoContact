package dev.theolm.wwc.presentation.ui.dialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.theolm.wwc.domain.models.DefaultApp
import dev.theolm.wwc.presentation.ext.checkIfWpBusinessIsInstalled
import dev.theolm.wwc.presentation.ext.checkIfWpIsInstalled
import dev.theolm.wwc.presentation.ext.getSharedPhoneNumber
import dev.theolm.wwc.presentation.ext.startWhatsAppChat
import dev.theolm.wwc.presentation.ui.dialog.error.ErrorDialog
import dev.theolm.wwc.presentation.ui.dialog.phoneinput.PhoneInputDialog
import dev.theolm.wwc.presentation.ui.theme.AppTheme
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
                        initialNumber = intent.getSharedPhoneNumber(),
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
