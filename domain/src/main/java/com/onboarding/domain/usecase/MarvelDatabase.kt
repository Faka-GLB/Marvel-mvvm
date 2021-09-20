package com.onboarding.domain.usecase

import com.onboarding.domain.entity.MarvelCharacter

interface MarvelDatabase {
    fun createOrUpdateCharacters(characters: List<MarvelCharacter>)
    fun getAllCharacters() : List<MarvelCharacter>
}
