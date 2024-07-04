package dev.theolm.wwc.presentation.di

import dev.theolm.wwc.presentation.ui.dialog.phoneinput.InputDialogViewModel
import dev.theolm.wwc.presentation.ui.settings.defaultapp.DefaultAppViewModel
import dev.theolm.wwc.presentation.ui.settings.defaultcode.DefaultCodeViewModel
import dev.theolm.wwc.presentation.ui.settings.home.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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
            observeSelectedCountryUseCase = get()
        )
    }
}
