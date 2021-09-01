package com.onboarding.domain.usecase

import com.onboarding.domain.entity.Base
import com.onboarding.domain.util.Result

interface GetAllCharactersUseCase {
    fun getCharacters(): Result<Base>
}

class GetAllCharactersUseCaseImpl(private val service: MarvelRepository) : GetAllCharactersUseCase {
    override fun getCharacters() = service.getCharacterInfo()
}
