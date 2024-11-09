package dev.theolm.wwc.di

import dev.theolm.wwc.ui.dialog.phoneinput.InputDialogViewModel
import dev.theolm.wwc.ui.settings.defaultapp.DefaultAppViewModel
import dev.theolm.wwc.ui.settings.defaultcode.DefaultCodeViewModel
import dev.theolm.wwc.ui.settings.history.HistoryViewModel
import dev.theolm.wwc.ui.settings.home.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

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
            observeSelectedCountryUseCase = get(),
            addHistoryUseCase = get()
        )
    }

    viewModel {
        HistoryViewModel(
            observeHistoryUseCase = get(),
            observeSelectedAppUseCase = get(),
            addHistoryUseCase = get()
        )
    }
}
