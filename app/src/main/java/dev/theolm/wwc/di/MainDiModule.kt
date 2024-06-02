package dev.theolm.wwc.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dev.theolm.wwc.core.storage.AppDataStore
import dev.theolm.wwc.core.storage.AppDataStoreImpl
import dev.theolm.wwc.core.storage.AppSettings
import dev.theolm.wwc.core.storage.AppSettingsSerializer
import dev.theolm.wwc.core.storage.DataStoreFileName
import dev.theolm.wwc.ui.main.dialog.input.InputDialogViewModel
import dev.theolm.wwc.ui.main.settings.defaultcode.DefaultCodeViewModel
import dev.theolm.wwc.ui.main.settings.home.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainDiModule = module {
    single<DataStore<AppSettings>> {
        DataStoreFactory.create(
            serializer = AppSettingsSerializer,
            produceFile = { get<Context>().dataStoreFile(DataStoreFileName) },
        )
    }

    single<AppDataStore> {
        AppDataStoreImpl(dataStore = get())
    }

    viewModel {
        DefaultCodeViewModel(
            appDataStore = get()
        )
    }

    viewModel {
        SettingsViewModel(
            appDataStore = get()
        )
    }

    viewModel {
        InputDialogViewModel(
            appDataStore = get()
        )
    }
}
