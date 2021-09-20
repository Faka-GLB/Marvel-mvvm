package com.onboarding.data.database

import com.onboarding.data.database.entity.MarvelCharacterRealm
import com.onboarding.data.mapper.transform
import com.onboarding.data.mapper.transformToRealm
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.domain.usecase.MarvelDatabase
import io.realm.Realm

class CharacterDatabase : MarvelDatabase {
    override fun createOrUpdateCharacters(characters: List<MarvelCharacter>) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                characters.forEach { marvelCharacter ->
                    realm.insertOrUpdate(marvelCharacter.transformToRealm())
                }
            }
        }
    }

    override fun getAllCharacters(): List<MarvelCharacter> {
        Realm.getDefaultInstance().use {
            var charactersList = mutableListOf<MarvelCharacter>()
            it.executeTransaction { realm ->
                charactersList = realm.where(MarvelCharacterRealm::class.java).findAll()
                    .mapTo(mutableListOf(), { character -> character.transform() })
            }
            return charactersList
        }
    }
}
