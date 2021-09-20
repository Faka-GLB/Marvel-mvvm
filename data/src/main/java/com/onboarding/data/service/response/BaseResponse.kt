package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.util.ConstantUtil

data class BaseResponse (
    @SerializedName("code")
    val code: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("status")
    val status: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("copyright")
    val copyRight: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("attributionText")
    val attributionText: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("attributionHTML")
    val attributionHTML: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("etag")
    val etag: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("data")
    val data: DataResponse = DataResponse()
)
