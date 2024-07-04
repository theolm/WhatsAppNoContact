package dev.theolm.wwc.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import dev.theolm.wwc.data.datasource.AppDataStore
import dev.theolm.wwc.data.datasource.AppDataStoreImpl
import dev.theolm.wwc.data.datasource.DataStoreFileName
import dev.theolm.wwc.data.repository.AppSettingsRepositoryImpl
import dev.theolm.wwc.data.serializer.AppSettingsSerializer
import dev.theolm.wwc.domain.models.AppSettings
import dev.theolm.wwc.domain.repository.AppSettingsRepository
import dev.theolm.wwc.presentation.ui.main.dialog.input.InputDialogViewModel
import dev.theolm.wwc.presentation.ui.settings.defaultapp.DefaultAppViewModel
import dev.theolm.wwc.presentation.ui.settings.defaultcode.DefaultCodeViewModel
import dev.theolm.wwc.presentation.ui.settings.home.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single<DataStore<AppSettings>> {
        DataStoreFactory.create(
            serializer = AppSettingsSerializer,
            produceFile = { get<Context>().dataStoreFile(DataStoreFileName) },
        )
    }

    single<AppDataStore> {
        AppDataStoreImpl(dataStore = get())
    }

    single<AppSettingsRepository> {
        AppSettingsRepositoryImpl(
            dataSource = get()
        )
    }
}



val presentationModule = module {
    viewModel {
        DefaultCodeViewModel(
            observeSelectedCountry = get(),
            updateSelectedCountry = get(),
        )
    }

    viewModel {
        DefaultAppViewModel(
            observeSelectedAppUseCase = get(),
            updateSelectedAppUseCase = get()
        )
    }

    viewModel {
        SettingsViewModel(
            observeSettings = get(),
        )
    }

    viewModel {
        InputDialogViewModel(
            observeSelectedAppUseCase = get(),
            observeSelectedCountryUseCase = get()
        )
    }
}
