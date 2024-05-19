package dev.theolm.wwc.ui.core.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

private val keyDefaultCode = stringPreferencesKey("default_code")
suspend fun DataStore<Preferences>.saveDefaultCode(code: String) {
    edit { settings ->
        settings[keyDefaultCode] = code
    }
}

suspend fun DataStore<Preferences>.getDefaultCode(): String? {
    return data.first()[keyDefaultCode]
}