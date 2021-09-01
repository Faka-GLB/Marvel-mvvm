package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.utils.ConstantUtil

data class CollectionResponse(
    @SerializedName("available")
    val available: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("collectionURI")
    val collection: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("items")
    val items: List<ItemResponse> = emptyList(),

    @SerializedName("returned")
    val returned: Int = ConstantUtil.DEFAULT_INT_VALUE
)
