package com.onboarding.data

import com.onboarding.data.mapper.transform
import com.onboarding.data.service.response.BaseResponse
import com.onboarding.data.service.response.CharacterResponse
import com.onboarding.data.service.response.CollectionResponse
import com.onboarding.data.service.response.DataResponse
import com.onboarding.data.service.response.ItemResponse
import com.onboarding.data.service.response.ThumbnailResponse
import com.onboarding.data.service.response.UrlsResponse
import com.onboarding.data.util.ConstantUtil.INT_VALUE
import com.onboarding.data.util.ConstantUtil.STRING_VALUE
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MarvelMapperTest {

    @Test
    fun `collection item transform`() {
        val itemTransformed = baseResponseTest.transform().marvelData.character.first().comics.items.first()
        assertEquals(STRING_VALUE, itemTransformed.name)
        assertEquals(STRING_VALUE, itemTransformed.resourceURI)
        assertEquals(STRING_VALUE, itemTransformed.type)
    }

    @Test
    fun `collection response transform`() {
        val collectionTransformed = baseResponseTest.transform().marvelData.character.first().comics
        assertEquals(INT_VALUE, collectionTransformed.available)
        assertEquals(STRING_VALUE, collectionTransformed.collection)
        assertEquals(INT_VALUE, collectionTransformed.returned)
    }

    @Test
    fun `thumbnail response transform`() {
        val thumbnailTransformed = baseResponseTest.transform().marvelData.character.first().thumbnail
        assertEquals(STRING_VALUE, thumbnailTransformed.path)
        assertEquals(STRING_VALUE, thumbnailTransformed.extension)
    }

    @Test
    fun `urls response transform`() {
        val urlTransformed = baseResponseTest.transform().marvelData.character.first().urls.first()
        assertEquals(STRING_VALUE, urlTransformed.type)
        assertEquals(STRING_VALUE, urlTransformed.url)
    }

    @Test
    fun `character response transform`() {
        val characterTransformed = baseResponseTest.transform().marvelData.character.first()
        assertEquals(INT_VALUE, characterTransformed.id)
        assertEquals(STRING_VALUE, characterTransformed.name)
        assertEquals(STRING_VALUE, characterTransformed.description)
        assertEquals(STRING_VALUE, characterTransformed.characterURI)
    }

    @Test
    fun `data response transform`() {
        val dataTransformed = baseResponseTest.transform().marvelData
        assertEquals(INT_VALUE, dataTransformed.offset)
        assertEquals(INT_VALUE, dataTransformed.limit)
        assertEquals(INT_VALUE, dataTransformed.total)
        assertEquals(INT_VALUE, dataTransformed.count)
    }

    @Test
    fun `base response transform`() {
        val baseTransformed = baseResponseTest.transform()
        assertEquals(INT_VALUE, baseTransformed.code)
        assertEquals(STRING_VALUE, baseTransformed.status)
        assertEquals(STRING_VALUE, baseTransformed.copyRight)
        assertEquals(STRING_VALUE, baseTransformed.attributionText)
        assertEquals(STRING_VALUE, baseTransformed.etag)
    }

    companion object {
        private val itemResponseTest = ItemResponse(
            resourceURI = STRING_VALUE,
            name = STRING_VALUE,
            type = STRING_VALUE
        )

        private val collectionResponseTest = CollectionResponse(
            available = INT_VALUE,
            collection = STRING_VALUE,
            items = listOf(itemResponseTest),
            returned = INT_VALUE
        )

        private val thumbnailTest = ThumbnailResponse(
            path = STRING_VALUE,
            extension = STRING_VALUE
        )

        private val urlsTest = UrlsResponse(
            type = STRING_VALUE,
            url = STRING_VALUE
        )

        private val characterTest = CharacterResponse(
            id = INT_VALUE,
            name = STRING_VALUE,
            description = STRING_VALUE,
            modified = STRING_VALUE,
            thumbnail = thumbnailTest,
            characterURI = STRING_VALUE,
            comics = collectionResponseTest,
            series = collectionResponseTest,
            stories = collectionResponseTest,
            events = collectionResponseTest,
            urls = listOf(urlsTest)
        )

        private val dataTest = DataResponse(
            offset = INT_VALUE,
            limit = INT_VALUE,
            total = INT_VALUE,
            count = INT_VALUE,
            character = listOf(characterTest)
        )

        private val baseResponseTest = BaseResponse(
            code = INT_VALUE,
            status = STRING_VALUE,
            copyRight = STRING_VALUE,
            attributionText = STRING_VALUE,
            attributionHTML = STRING_VALUE,
            etag = STRING_VALUE,
            data = dataTest
        )
    }
}
