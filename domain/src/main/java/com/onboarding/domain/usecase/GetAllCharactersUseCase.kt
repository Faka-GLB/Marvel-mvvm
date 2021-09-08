package com.onboarding.domain.usecase

import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.domain.util.Result

interface GetAllCharactersUseCase {
    fun getCharacters(): Result<List<MarvelCharacter>>
}

class GetAllCharactersUseCaseImpl(private val service: MarvelRepository, private val database: MarvelDatabase) : GetAllCharactersUseCase {

    override fun getCharacters() = when (val baseResponse = service.getCharacterInfo()) {
        is Result.Success -> {
            baseResponse.data.marvelData.character.forEach { character ->
                database.createOrUpdateCharacter(character)
            }
            Result.Success(baseResponse.data.marvelData.character)
        }
        is Result.Failure -> {
            Result.Success(database.getAllCharacters())
        }
    }
}
