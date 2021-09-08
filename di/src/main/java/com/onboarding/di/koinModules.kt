package com.onboarding.di

import com.onboarding.data.database.CharacterDatabase
import com.onboarding.data.service.MarvelRepositoryImpl
import com.onboarding.domain.usecase.GetAllCharactersUseCase
import com.onboarding.domain.usecase.GetAllCharactersUseCaseImpl
import com.onboarding.domain.usecase.MarvelDatabase
import com.onboarding.domain.usecase.MarvelRepository
import org.koin.dsl.module

val useCasesModule = module {
    single<GetAllCharactersUseCase> { GetAllCharactersUseCaseImpl(get(), get()) }
}

val repositoryModule = module {
    single<MarvelDatabase> { CharacterDatabase() }
    single<MarvelRepository> { MarvelRepositoryImpl() }
}
