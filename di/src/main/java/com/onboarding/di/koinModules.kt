package com.onboarding.di

import com.onboarding.data.service.MarvelRepositoryImpl
import com.onboarding.domain.usecase.GetAllCharactersUseCase
import com.onboarding.domain.usecase.GetAllCharactersUseCaseImpl
import com.onboarding.domain.usecase.MarvelRepository
import org.koin.dsl.module

val useCasesModule = module {
    single<GetAllCharactersUseCase> { GetAllCharactersUseCaseImpl(get()) }
}

val repositoryModule = module {
    single<MarvelRepository> { MarvelRepositoryImpl() }
}
