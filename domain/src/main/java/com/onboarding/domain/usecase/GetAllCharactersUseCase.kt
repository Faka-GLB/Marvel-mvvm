package com.onboarding.domain.usecase

import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.domain.util.Result

interface GetAllCharactersUseCase {
    fun getCharacters(fromRemote: Boolean): Result<List<MarvelCharacter>>
}

class GetAllCharactersUseCaseImpl(private val service: MarvelRepository, private val database: MarvelDatabase) : GetAllCharactersUseCase {

    override fun getCharacters(fromRemote: Boolean): Result<List<MarvelCharacter>> =
        if (fromRemote) {
            when (val baseResponse = service.getCharacterInfo()) {
                is Result.Success -> {
                    database.createOrUpdateCharacters(baseResponse.data.marvelData.character)
                    Result.Success(baseResponse.data.marvelData.character)
                }
                is Result.Failure -> {
                    Result.Failure(baseResponse.exception)
                }
            }
        } else {
            Result.Success(database.getAllCharacters())
        }
}
