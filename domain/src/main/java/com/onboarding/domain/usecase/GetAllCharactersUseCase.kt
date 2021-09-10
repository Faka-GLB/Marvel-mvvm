package com.onboarding.domain.usecase

import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.domain.util.Result
import java.lang.Exception

interface GetAllCharactersUseCase {
    fun getCharacters(fromRemote: Boolean): Result<List<MarvelCharacter>>
}

class GetAllCharactersUseCaseImpl(private val service: MarvelRepository, private val database: MarvelDatabase) : GetAllCharactersUseCase {

    override fun getCharacters(fromRemote: Boolean): Result<List<MarvelCharacter>> {
        if (fromRemote) {
            return when (val baseResponse = service.getCharacterInfo()) {
                is Result.Success -> {
                    baseResponse.data.marvelData.character.forEach { character ->
                        database.createOrUpdateCharacter(character)
                    }
                    Result.Success(baseResponse.data.marvelData.character)
                }
                is Result.Failure -> {
                    Result.Failure(baseResponse.exception)
                }
            }
        }
        else {
            return Result.Success(database.getAllCharacters())
        }
    }
}
