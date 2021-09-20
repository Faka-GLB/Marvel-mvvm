package com.onboarding.data.mapper

import com.onboarding.data.database.entity.CollectionItemRealm
import com.onboarding.data.database.entity.MarvelCharacterRealm
import com.onboarding.data.database.entity.MarvelCollectionRealm
import com.onboarding.data.database.entity.ThumbnailRealm
import com.onboarding.data.database.entity.UrlsRealm
import com.onboarding.data.util.ConstantUtil
import com.onboarding.domain.entity.CollectionItem
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.domain.entity.MarvelCollection
import com.onboarding.domain.entity.Thumbnail
import com.onboarding.domain.entity.Urls
import io.realm.RealmList

fun MarvelCharacter.transformToRealm() = MarvelCharacterRealm(
    this.id,
    this.name,
    this.description,
    this.modified,
    this.thumbnail.transformToRealm(),
    this.characterURI,
    this.comics.transformToRealm(),
    this.series.transformToRealm(),
    this.stories.transformToRealm(),
    this.events.transformToRealm(),
    this.urls.mapTo(RealmList<UrlsRealm>(), { it.transformToRealm() })
)

private fun Thumbnail.transformToRealm() = ThumbnailRealm(
    this.path,
    this.extension
)

private fun MarvelCollection.transformToRealm() = MarvelCollectionRealm(
    this.available,
    this.collection,
    this.items.mapTo(RealmList<CollectionItemRealm>(), { it.transformToRealm() }),
    this.returned
)

private fun CollectionItem.transformToRealm() = CollectionItemRealm(
    this.resourceURI,
    this.name,
    this.type
)

private fun Urls.transformToRealm() = UrlsRealm(
    this.type,
    this.url
)

fun MarvelCharacterRealm.transform() = MarvelCharacter(
    this.id,
    this.name,
    this.description,
    this.modified,
    this.thumbnail?.transform() ?: getEmptyThumbnail(),
    this.characterURI,
    this.comics?.transform() ?: getEmptyCollection(),
    this.series?.transform() ?: getEmptyCollection(),
    this.stories?.transform() ?: getEmptyCollection(),
    this.events?.transform() ?: getEmptyCollection(),
    this.urls.mapTo(mutableListOf(), { it.transform() })
)

private fun ThumbnailRealm.transform() = Thumbnail(
    this.path,
    this.extension
)

private fun MarvelCollectionRealm.transform() = MarvelCollection(
    this.available,
    this.collection,
    this.items.mapTo(mutableListOf(), { it.transform() }),
    this.returned
)

private fun CollectionItemRealm.transform() = CollectionItem(
    this.resourceURI,
    this.name,
    this.type
)

private fun UrlsRealm.transform() = Urls(
    this.type,
    this.url
)

private fun getEmptyThumbnail() = Thumbnail(
    path = ConstantUtil.DEFAULT_STRING_VALUE,
    extension = ConstantUtil.DEFAULT_STRING_VALUE
)

private fun getEmptyCollection() = MarvelCollection(
    available = ConstantUtil.DEFAULT_INT_VALUE,
    collection = ConstantUtil.DEFAULT_STRING_VALUE,
    items = emptyList(),
    returned = ConstantUtil.DEFAULT_INT_VALUE
)
