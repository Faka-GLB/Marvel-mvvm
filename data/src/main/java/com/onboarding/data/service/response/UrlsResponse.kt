package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.util.ConstantUtil

data class UrlsResponse(
    @SerializedName("type")
    val type: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("url")
    val url: String = ConstantUtil.DEFAULT_STRING_VALUE
)
