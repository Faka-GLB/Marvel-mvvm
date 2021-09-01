package com.onboarding.data.mapper

import com.onboarding.data.service.response.BaseResponse
import com.onboarding.data.service.response.CharacterResponse
import com.onboarding.data.service.response.CollectionResponse
import com.onboarding.data.service.response.DataResponse
import com.onboarding.data.service.response.ItemResponse
import com.onboarding.data.service.response.ThumbnailResponse
import com.onboarding.data.service.response.UrlsResponse
import com.onboarding.domain.entity.Base
import com.onboarding.domain.entity.CollectionItem
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.domain.entity.MarvelCollection
import com.onboarding.domain.entity.MarvelData
import com.onboarding.domain.entity.Thumbnail
import com.onboarding.domain.entity.Urls

fun BaseResponse.transform() = Base(
    this.code,
    this.status,
    this.copyRight,
    this.attributionText,
    this.asttributionHTML,
    this.etag,
    this.data.transform()
)

private fun DataResponse.transform() = MarvelData(
    this.offset,
    this.limit,
    this.total,
    this.count,
    this.character.transform()
)

@JvmName("transformCharacterResponse")
private fun List<CharacterResponse>.transform() = this.map { it.transform() }

private fun CharacterResponse.transform() = MarvelCharacter(
    this.id,
    this.name,
    this.description,
    this.modified,
    this.thumbnail.transform(),
    this.characterURI,
    this.comics.transform(),
    this.series.transform(),
    this.stories.transform(),
    this.events.transform(),
    this.urls.transform()
)

private fun ThumbnailResponse.transform() = Thumbnail(
    this.path,
    this.extension
)

private fun CollectionResponse.transform() = MarvelCollection(
    this.available,
    this.collection,
    this.items.transform(),
    this.returned
)

@JvmName("transformItemResponse")
private fun List<ItemResponse>.transform() = this.map { it.transform() }

private fun ItemResponse.transform() = CollectionItem(
    this.resourceURI,
    this.name,
    this.type
)

private fun UrlsResponse.transform() = Urls(
    this.type,
    this.url
)

private fun List<UrlsResponse>.transform() = this.map { it.transform() }
