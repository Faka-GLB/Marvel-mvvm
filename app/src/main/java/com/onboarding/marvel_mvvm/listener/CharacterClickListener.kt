package com.onboarding.marvel_mvvm.listener

import com.onboarding.domain.entity.MarvelCharacter

interface CharacterClickListener {
    fun onCharacterClick(character: MarvelCharacter)
}
