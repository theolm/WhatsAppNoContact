package dev.theolm.wwc.presentation.ui.settings.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.theolm.wwc.R
import dev.theolm.wwc.presentation.ui.components.ListScreen
import dev.theolm.wwc.presentation.ui.components.roundedItem
import dev.theolm.wwc.presentation.ext.getVersionName
import dev.theolm.wwc.presentation.ext.openBrowser

private const val RepositoryUrl = "https://github.com/theolm/WhatsAppNoContact"
private const val ReleasesUrl = "$RepositoryUrl/releases"
private const val IssuesUrl = "$RepositoryUrl/issues"
private const val PrivacyPolicyUrl = "$RepositoryUrl/blob/main/privacy_policy.md"

@Suppress("LongMethod", "ForbiddenComment")
@Composable
fun AboutPage(
    onBackPress: () -> Unit,
) {
    val context = LocalContext.current
    ListScreen(
        title = stringResource(id = R.string.about),
        onBackPress = onBackPress
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(150.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        item {
            HorizontalDivider()
        }

        item {
            val versionName = context.getVersionName()
            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .roundedItem { },
                headlineContent = { Text(text = stringResource(id = R.string.version)) },
                supportingContent = { Text(text = versionName) }
            )

            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .roundedItem {
                        context.openBrowser(ReleasesUrl)
                    },
                headlineContent = {
                    Text(text = stringResource(id = R.string.change_log))
                },
            )

            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .roundedItem {
                        context.openBrowser(IssuesUrl)
                    },
                headlineContent = {
                    Text(text = stringResource(id = R.string.report_bug))
                },
            )

            // TODO: Open source licenses
//            ListItem(
//                headlineContent = { Text(text = stringResource(id = R.string.open_source_licenses)) },
//            )

            ListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .roundedItem {
                        context.openBrowser(PrivacyPolicyUrl)
                    },
                headlineContent = {
                    Text(text = stringResource(id = R.string.privacy_policy))
                },
            )
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    onClick = {
                        context.openBrowser(RepositoryUrl)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.github_logo),
                        contentDescription = stringResource(id = R.string.github),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AboutPage {}
}
