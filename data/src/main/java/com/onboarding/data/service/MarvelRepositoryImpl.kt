package com.onboarding.data.service

import com.onboarding.data.BuildConfig.HASH_MARVEL_KEY
import com.onboarding.data.BuildConfig.PUBLIC_MARVEL_KEY
import com.onboarding.data.mapper.transform
import com.onboarding.data.service.api.MarvelApi
import com.onboarding.domain.entity.Base
import com.onboarding.domain.usecase.MarvelRepository
import com.onboarding.domain.util.Result

class MarvelRepositoryImpl() : MarvelRepository {
    private val generator = MarvelRequestGenerator()
    override fun getCharacterInfo(): Result<Base> {
        val callResponse = generator.createService(MarvelApi::class.java)
            .callCharacterInfo(timestamp = TS_VALUE, publicKey = PUBLIC_MARVEL_KEY, hash = HASH_MARVEL_KEY)
        try {
            val response = callResponse.execute()
            response.body()?.let {
                return Result.Success(it.transform())
            }
        } catch (e: Exception) {
            return Result.Failure(Exception(EXCEPTION_MESSAGE))
        }
        return Result.Failure(Exception(EXCEPTION_MESSAGE))
    }

    companion object {
        private const val TS_VALUE = 1
        private const val EXCEPTION_MESSAGE: String = "Bad request/response"
    }
}
