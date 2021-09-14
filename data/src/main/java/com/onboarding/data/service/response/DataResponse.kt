package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.util.ConstantUtil

data class DataResponse(
    @SerializedName("offset")
    val offset: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("limit")
    val limit: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("total")
    val total: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("count")
    val count: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("results")
    val character: List<CharacterResponse> = emptyList()
)
