package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.util.ConstantUtil

data class ItemResponse(
    @SerializedName("resourceURI")
    val resourceURI: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("name")
    val name: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("type")
    val type: String = ConstantUtil.DEFAULT_STRING_VALUE
)
