package dev.theolm.wwc.domain.di

import dev.theolm.wwc.domain.usecase.ObserveSelectedAppUseCase
import dev.theolm.wwc.domain.usecase.ObserveSelectedAppUseCaseImpl
import dev.theolm.wwc.domain.usecase.ObserveSelectedCountryUseCase
import dev.theolm.wwc.domain.usecase.ObserveSelectedCountryUseCaseImpl
import dev.theolm.wwc.domain.usecase.ObserveSettingsUseCase
import dev.theolm.wwc.domain.usecase.ObserveSettingsUseCaseImpl
import dev.theolm.wwc.domain.usecase.UpdateSelectedAppUseCase
import dev.theolm.wwc.domain.usecase.UpdateSelectedAppUseCaseImpl
import dev.theolm.wwc.domain.usecase.UpdateSelectedCountryUseCase
import dev.theolm.wwc.domain.usecase.UpdateSelectedCountryUseCaseImpl
import dev.theolm.wwc.domain.usecase.UpdateSettingsUseCase
import dev.theolm.wwc.domain.usecase.UpdateSettingsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<ObserveSelectedCountryUseCase> {
        ObserveSelectedCountryUseCaseImpl(repository = get())
    }

    factory<ObserveSettingsUseCase> {
        ObserveSettingsUseCaseImpl(repository = get())
    }

    factory<ObserveSelectedAppUseCase> {
        ObserveSelectedAppUseCaseImpl(repository = get())
    }

    factory<UpdateSelectedCountryUseCase> {
        UpdateSelectedCountryUseCaseImpl(repository = get())
    }

    factory<UpdateSelectedAppUseCase> {
        UpdateSelectedAppUseCaseImpl(repository = get())
    }

    factory<UpdateSettingsUseCase> {
        UpdateSettingsUseCaseImpl(repository = get())
    }
}
