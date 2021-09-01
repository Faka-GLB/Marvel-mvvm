package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.utils.ConstantUtil

data class CharacterResponse(
    @SerializedName("id")
    val id: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("name")
    val name: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("description")
    val description: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("modified")
    val modified: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse = ThumbnailResponse(),

    @SerializedName("resourceURI")
    val characterURI: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("comics")
    val comics: CollectionResponse = CollectionResponse(),

    @SerializedName("series")
    val series: CollectionResponse = CollectionResponse(),

    @SerializedName("stories")
    val stories: CollectionResponse = CollectionResponse(),

    @SerializedName("events")
    val events: CollectionResponse = CollectionResponse(),

    @SerializedName("urls")
    val urls: List<UrlsResponse> = emptyList()
)
