package com.onboarding.data.service.api

import com.onboarding.data.service.response.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("characters?")
    fun callCharacterInfo(
        @Query("ts") timestamp: Int,
        @Query("apikey") publicKey: String,
        @Query("hash") hash: String
    ): Call<BaseResponse>
}
