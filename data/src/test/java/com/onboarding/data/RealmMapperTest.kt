package com.onboarding.data

import com.onboarding.data.database.entity.CollectionItemRealm
import com.onboarding.data.database.entity.MarvelCharacterRealm
import com.onboarding.data.database.entity.MarvelCollectionRealm
import com.onboarding.data.database.entity.ThumbnailRealm
import com.onboarding.data.database.entity.UrlsRealm
import com.onboarding.data.mapper.transform
import com.onboarding.data.mapper.transformToRealm
import com.onboarding.data.util.ConstantUtil
import com.onboarding.data.util.ConstantUtil.INT_VALUE
import com.onboarding.data.util.ConstantUtil.STRING_VALUE
import com.onboarding.domain.entity.CollectionItem
import com.onboarding.domain.entity.MarvelCharacter
import com.onboarding.domain.entity.MarvelCollection
import com.onboarding.domain.entity.Thumbnail
import com.onboarding.domain.entity.Urls
import io.realm.RealmList
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RealmMapperTest {

    @Test
    fun `collection item transform to realm`() {
        val itemTransformed = characterTest.transformToRealm().comics?.items?.first()
        assertEquals(STRING_VALUE, itemTransformed?.resourceURI)
        assertEquals(STRING_VALUE, itemTransformed?.name)
        assertEquals(STRING_VALUE, itemTransformed?.type)
        characterTest.transformToRealm().transform()
    }

    @Test
    fun `collection item realm transform to domain entity`() {
        val realmItemTransformed = characterRealmTest.transform().comics.items.first()
        assertEquals(STRING_VALUE, realmItemTransformed.resourceURI)
        assertEquals(STRING_VALUE, realmItemTransformed.name)
        assertEquals(STRING_VALUE, realmItemTransformed.type)
    }

    @Test
    fun `marvel collection transform to realm`() {
        val collectionTransformed = characterTest.transformToRealm().comics
        assertEquals(INT_VALUE, collectionTransformed?.available)
        assertEquals(STRING_VALUE, collectionTransformed?.collection)
        assertEquals(INT_VALUE, collectionTransformed?.returned)
    }

    @Test
    fun `realm collection transform to domain entity`() {
        val realmCollectionTransformed = characterRealmTest.transform().comics
        assertEquals(INT_VALUE, realmCollectionTransformed.available)
        assertEquals(STRING_VALUE, realmCollectionTransformed.collection)
        assertEquals(INT_VALUE, realmCollectionTransformed.returned)
    }

    @Test
    fun `null realm collection transform to domain entity`() {
        val realmCollectionTransformed = MarvelCharacterRealm(comics = null).transform().comics
        assertEquals(ConstantUtil.DEFAULT_INT_VALUE, realmCollectionTransformed.available)
        assertEquals(ConstantUtil.DEFAULT_STRING_VALUE, realmCollectionTransformed.collection)
        assertEquals(ConstantUtil.DEFAULT_INT_VALUE, realmCollectionTransformed.returned)
        assertEquals(EMPTY_LIST_SIZE, realmCollectionTransformed.items.size)
    }

    @Test
    fun `marvel thumbnail transform to realm`() {
        val thumbnailTransformed = characterTest.transformToRealm().thumbnail
        assertEquals(STRING_VALUE, thumbnailTransformed?.path)
        assertEquals(STRING_VALUE, thumbnailTransformed?.extension)
    }

    @Test
    fun `realm thumbnail transform to domain entity`() {
        val realmThumbnailTransformed = characterRealmTest.transform().thumbnail
        assertEquals(STRING_VALUE, realmThumbnailTransformed.path)
        assertEquals(STRING_VALUE, realmThumbnailTransformed.extension)
    }

    @Test
    fun `null realm thumbnail transform to domain entity`() {
        val realmCollectionTransformed = MarvelCharacterRealm(thumbnail = null).transform().thumbnail
        assertEquals(ConstantUtil.DEFAULT_STRING_VALUE, realmCollectionTransformed.path)
        assertEquals(ConstantUtil.DEFAULT_STRING_VALUE, realmCollectionTransformed.extension)
    }

    @Test
    fun `urls transform to realm`() {
        val urlsTransformed = characterTest.transformToRealm().urls.first()
        assertEquals(STRING_VALUE, urlsTransformed?.type)
        assertEquals(STRING_VALUE, urlsTransformed?.url)
    }

    @Test
    fun `realm urls transform to domain entity`() {
        val realmUrlsTransformed = characterRealmTest.transform().urls.first()
        assertEquals(STRING_VALUE, realmUrlsTransformed.type)
        assertEquals(STRING_VALUE, realmUrlsTransformed.url)
    }

    @Test
    fun `marvel character transform to realm`() {
        val characterTransformed = characterTest.transformToRealm()
        assertEquals(INT_VALUE, characterTransformed.id)
        assertEquals(STRING_VALUE, characterTransformed.name)
        assertEquals(STRING_VALUE, characterTransformed.description)
        assertEquals(STRING_VALUE, characterTransformed.characterURI)
    }

    @Test
    fun `realm character transform to domain entity`() {
        val realmCharacterTransformed = characterRealmTest.transform()
        assertEquals(INT_VALUE, realmCharacterTransformed.id)
        assertEquals(STRING_VALUE, realmCharacterTransformed.name)
        assertEquals(STRING_VALUE, realmCharacterTransformed.description)
        assertEquals(STRING_VALUE, realmCharacterTransformed.characterURI)
    }

    companion object {
        private const val EMPTY_LIST_SIZE = 0

        private val itemTest = CollectionItem(
            resourceURI = STRING_VALUE,
            name = STRING_VALUE,
            type = STRING_VALUE
        )

        private val collectionTest = MarvelCollection(
            available = INT_VALUE,
            collection = STRING_VALUE,
            items = listOf(itemTest),
            returned = INT_VALUE
        )

        private val thumbnailTest = Thumbnail(
            path = STRING_VALUE,
            extension = STRING_VALUE
        )

        private val urlsTest = Urls(
            type = STRING_VALUE,
            url = STRING_VALUE
        )

        private val characterTest = MarvelCharacter(
            id = INT_VALUE,
            name = STRING_VALUE,
            description = STRING_VALUE,
            modified = STRING_VALUE,
            thumbnail = thumbnailTest,
            characterURI = STRING_VALUE,
            comics = collectionTest,
            series = collectionTest,
            stories = collectionTest,
            events = collectionTest,
            urls = listOf(urlsTest)
        )

        private val itemRealmTest = CollectionItemRealm(
            resourceURI = STRING_VALUE,
            name = STRING_VALUE,
            type = STRING_VALUE
        )
        private val collectionRealmTest = MarvelCollectionRealm(
            available = INT_VALUE,
            collection = STRING_VALUE,
            items = RealmList(itemRealmTest),
            returned = INT_VALUE
        )

        private val thumbnailRealmTest = ThumbnailRealm(
            path = STRING_VALUE,
            extension = STRING_VALUE
        )

        private val urlsRealmTest = UrlsRealm(
            type = STRING_VALUE,
            url = STRING_VALUE
        )

        private val characterRealmTest = MarvelCharacterRealm(
            id = INT_VALUE,
            name = STRING_VALUE,
            description = STRING_VALUE,
            modified = STRING_VALUE,
            thumbnail = thumbnailRealmTest,
            characterURI = STRING_VALUE,
            comics = collectionRealmTest,
            series = collectionRealmTest,
            stories = collectionRealmTest,
            events = collectionRealmTest,
            urls = RealmList(urlsRealmTest)
        )
    }
}
