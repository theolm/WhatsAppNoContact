package dev.theolm.wwc.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.theolm.wwc.core.whats.checkIfWpIsInstalled
import dev.theolm.wwc.core.whats.startWhatsAppChat
import dev.theolm.wwc.ui.main.dialog.error.ErrorDialog
import dev.theolm.wwc.ui.main.dialog.input.InputDialog
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
                if (checkIfWpIsInstalled()) {
                    InputDialog(
                        onDismiss = {
                            finish()
                        },
                        onStart = {
                            onStartChatClicked(it)
                        }
                    )
                } else {
                    ErrorDialog(onDismiss = { finish() })
                }
            }
        }
    }

    private fun onStartChatClicked(input: String) = launch {
        startWhatsAppChat(input)
    }
}
