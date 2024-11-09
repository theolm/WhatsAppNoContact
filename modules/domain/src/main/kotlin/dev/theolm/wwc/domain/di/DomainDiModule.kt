package dev.theolm.wwc.domain.di

import dev.theolm.wwc.domain.usecase.AddHistoryUseCase
import dev.theolm.wwc.domain.usecase.AddHistoryUseCaseImpl
import dev.theolm.wwc.domain.usecase.ClearHistoryUseCase
import dev.theolm.wwc.domain.usecase.ClearHistoryUseCaseImpl
import dev.theolm.wwc.domain.usecase.DeleteHistoryUseCase
import dev.theolm.wwc.domain.usecase.DeleteHistoryUseCaseImpl
import dev.theolm.wwc.domain.usecase.ObserveHistoryUseCase
import dev.theolm.wwc.domain.usecase.ObserveHistoryUseCaseImpl
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

    factory<ObserveHistoryUseCase> {
        ObserveHistoryUseCaseImpl(repository = get())
    }

    factory<AddHistoryUseCase> {
        AddHistoryUseCaseImpl(repository = get())
    }

    factory<DeleteHistoryUseCase> {
        DeleteHistoryUseCaseImpl(repository = get())
    }

    factory<ClearHistoryUseCase> {
        ClearHistoryUseCaseImpl(repository = get())
    }
}
