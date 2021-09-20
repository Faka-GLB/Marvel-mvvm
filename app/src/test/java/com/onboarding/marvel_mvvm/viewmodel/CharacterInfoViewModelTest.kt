package com.onboarding.marvel_mvvm.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.marvel_mvvm.utils.ConstantUtils.FIRST_STATUS
import com.onboarding.marvel_mvvm.utils.ConstantUtils.SECOND_STATUS
import com.onboarding.marvel_mvvm.utils.Event
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharacterInfoViewModelTest : ViewModelTest() {
    private lateinit var viewModel: CharacterInfoViewModel
    private val statusList = listOf<CharacterData<CharacterStatus>>(
        CharacterData(CharacterStatus.INIT),
        CharacterData(CharacterStatus.INIT_EMPTY_DESCRIPTION)
    )
    private lateinit var livedata: TestObserver<Event<CharacterData<MarvelCharacter>>>
    private val marvelCharacter: MarvelCharacter = mock()

    @Before
    override fun init() {
        super.init()
        viewModel = CharacterInfoViewModel()
    }

    @Test
    fun `viewModel - status - init`() {
        livedata = viewModel.getLiveDataCharacter().testObserver()
        whenever(marvelCharacter.description).thenReturn(DESCRIPTION)
        viewModel.init(marvelCharacter)
        assertEquals(statusList[FIRST_STATUS].responseType, livedata.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `viewModel - status - empty description init`() {
        livedata = viewModel.getLiveDataCharacter().testObserver()
        whenever(marvelCharacter.description).thenReturn(EMPTY_DESCRIPTION)
        viewModel.init(marvelCharacter)
        assertEquals(statusList[SECOND_STATUS].responseType, livedata.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
    }

    companion object {
        private const val DESCRIPTION = "Not empty"
        private const val EMPTY_DESCRIPTION = ""
    }
}
