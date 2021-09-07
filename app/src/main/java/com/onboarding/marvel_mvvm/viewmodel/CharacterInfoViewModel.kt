package com.onboarding.marvel_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.marvel_mvvm.utils.Event

class CharacterInfoViewModel : ViewModel() {
    private var liveDataCharacter: MutableLiveData<Event<CharacterData<MarvelCharacter>>> = MutableLiveData()

    fun init(character: MarvelCharacter) {
        if (character.description.isEmpty()) {
            liveDataCharacter.postValue(Event(CharacterData(responseType = CharacterStatus.INIT_EMPTY_DESCRIPTION, data = character)))
        } else {
            liveDataCharacter.postValue(Event(CharacterData(responseType = CharacterStatus.INIT, data = character)))
        }
    }

    fun getLiveDataCharacter() = liveDataCharacter
}

data class CharacterData<RequestData>(var responseType: CharacterStatus, var data: RequestData? = null, var error: Exception? = null)

enum class CharacterStatus {
    INIT, INIT_EMPTY_DESCRIPTION
}
