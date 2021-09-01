package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.utils.ConstantUtil

data class ThumbnailResponse (
    @SerializedName("path")
    val path: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("extension")
    val extension: String = ConstantUtil.DEFAULT_STRING_VALUE
)
