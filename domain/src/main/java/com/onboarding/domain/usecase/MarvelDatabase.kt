package com.onboarding.domain.usecase

import com.onboarding.domain.entity.MarvelCharacter

interface MarvelDatabase {
    fun createOrUpdateCharacter(character: MarvelCharacter)
    fun getAllCharacters() : List<MarvelCharacter>
}
