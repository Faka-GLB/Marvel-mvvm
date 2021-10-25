package com.onboarding.marvel_mvvm.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.onboarding.domain.entity.Base
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.domain.entity.MarvelData
import com.onboarding.domain.usecase.GetAllCharactersUseCase
import com.onboarding.domain.usecase.GetAllCharactersUseCaseImpl
import com.onboarding.domain.usecase.MarvelDatabase
import com.onboarding.domain.usecase.MarvelRepository
import com.onboarding.domain.util.Result
import com.onboarding.marvel_mvvm.utils.ConstantUtils.FIRST_STATUS
import com.onboarding.marvel_mvvm.utils.ConstantUtils.SECOND_STATUS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : ViewModelTest() {
    private lateinit var viewModel: MainViewModel
    private lateinit var getAllCharactersUseCase: GetAllCharactersUseCase
    private val marvelRepository: MarvelRepository = mock()
    private val database: MarvelDatabase = mock()
    private val baseMock: Base = mock()
    private val marvelDataMock: MarvelData = mock()

    @Before
    override fun init() {
        super.init()
        getAllCharactersUseCase = GetAllCharactersUseCaseImpl(marvelRepository, database)
        viewModel = MainViewModel(getAllCharactersUseCase)
    }

    @Test
    fun `getCharacters - remote - success`() {
        val liveData = viewModel.getLiveDataCharacters().testObserver()
        val successResult: Result.Success<Base> = mock()
        val charactersList: List<MarvelCharacter> = mock()
        val responseList = listOf<Data<Status>>(
            Data(Status.LOADING),
            Data(Status.SUCCESSFUL)
        )
        whenever(successResult.data).thenReturn(baseMock)
        whenever(baseMock.marvelData).thenReturn(marvelDataMock)
        whenever(marvelDataMock.character).thenReturn(charactersList)
        whenever(marvelRepository.getCharacterInfo()).thenReturn(successResult)
        runBlocking {
            viewModel.getCharacters(true).join()
        }
        verify(marvelRepository).getCharacterInfo()
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `get characters - remote - failure`() {
        val liveData = viewModel.getLiveDataCharacters().testObserver()
        val failureResult: Result.Failure = mock()
        val exception: Exception = mock()
        val responseList = listOf<Data<Status>>(
            Data(Status.LOADING),
            Data(Status.ERROR, error = exception)
        )
        whenever(failureResult.exception).thenReturn(exception)
        whenever(marvelRepository.getCharacterInfo()).thenReturn(failureResult)
        runBlocking {
            viewModel.getCharacters(true).join()
        }
        verify(marvelRepository).getCharacterInfo()
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `get characters - remote - empty response list`() {
        val liveData = viewModel.getLiveDataCharacters().testObserver()
        val successResult: Result.Success<Base> = mock()
        val emptyList = emptyList<MarvelCharacter>()
        val responseList = listOf<Data<Status>>(
            Data(Status.LOADING),
            Data(Status.EMPTY_RESPONSE_LIST)
        )
        whenever(marvelRepository.getCharacterInfo()).thenReturn(successResult)
        whenever(successResult.data).thenReturn(baseMock)
        whenever(baseMock.marvelData).thenReturn(marvelDataMock)
        whenever(marvelDataMock.character).thenReturn(emptyList)
        runBlocking {
            viewModel.getCharacters(true).join()
        }
        verify(marvelRepository).getCharacterInfo()
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `get characters - local`() {
        val liveData = viewModel.getLiveDataCharacters().testObserver()
        val charactersList: List<MarvelCharacter> = mock()
        val responseList = listOf<Data<Status>>(
            Data(Status.LOADING),
            Data(Status.SUCCESSFUL)
        )
        whenever(database.getAllCharacters()).thenReturn(charactersList)
        runBlocking {
            viewModel.getCharacters(false)
        }
        verify(database).getAllCharacters()
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
        assertEquals(charactersList, liveData.observedValues[SECOND_STATUS]?.peekContent()?.data)
    }
}
