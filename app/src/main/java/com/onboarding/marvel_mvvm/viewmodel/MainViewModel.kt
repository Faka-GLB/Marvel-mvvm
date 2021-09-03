package com.onboarding.marvel_mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onboarding.domain.entity.Base
import com.onboarding.domain.entity.MarvelData
import com.onboarding.domain.usecase.GetAllCharactersUseCase
import com.onboarding.domain.util.Result
import com.onboarding.marvel_mvvm.utils.Event
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getAllCharacters: GetAllCharactersUseCase) : ViewModel() {

    private var mutableLiveDataCharacters: MutableLiveData<Event<Data<MarvelData>>> = MutableLiveData()

    fun getLiveDataCharacters() = mutableLiveDataCharacters

    fun getCharacters() = viewModelScope.launch {
        mutableLiveDataCharacters.value = Event(Data(responseType = Status.LOADING))
        when (val result = withContext(IO) { getAllCharacters.getCharacters() }) {
            is Result.Success<Base> -> {
                mutableLiveDataCharacters.postValue(Event(Data(responseType = Status.SUCCESSFUL, data = result.data.marvelData)))
            }
            is Result.Failure -> {
                mutableLiveDataCharacters.postValue(Event(Data(responseType = Status.ERROR, error = result.exception)))
            }
        }
    }
}

data class Data<RequestData>(var responseType: Status, var data: RequestData? = null, var error: Exception? = null)

enum class Status { SUCCESSFUL, ERROR, LOADING }
