package dev.theolm.wwc.di

import dev.theolm.wwc.ui.main.settings.defaultcode.DefaultCodeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainDiModule = module {
    viewModel { DefaultCodeViewModel() }
}