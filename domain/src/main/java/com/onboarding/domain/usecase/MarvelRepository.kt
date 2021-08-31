package com.onboarding.domain.usecase

import com.onboarding.domain.entity.Base
import com.onboarding.domain.util.Result

interface MarvelRepository {
    fun getCharacterInfo(): Result<Base>
}
