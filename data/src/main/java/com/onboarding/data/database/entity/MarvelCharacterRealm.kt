package com.onboarding.data.database.entity

import com.onboarding.data.utils.ConstantUtil.DEFAULT_INT_VALUE
import com.onboarding.data.utils.ConstantUtil.DEFAULT_STRING_VALUE
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MarvelCharacterRealm(
    @PrimaryKey
    var id: Int = DEFAULT_INT_VALUE,
    var name: String = DEFAULT_STRING_VALUE,
    var description: String = DEFAULT_STRING_VALUE,
    var modified: String = DEFAULT_STRING_VALUE,
    var thumbnail: ThumbnailRealm? = ThumbnailRealm(),
    var characterURI: String = DEFAULT_STRING_VALUE,
    var comics: MarvelCollectionRealm? = MarvelCollectionRealm(),
    var series: MarvelCollectionRealm? = MarvelCollectionRealm(),
    var stories: MarvelCollectionRealm? = MarvelCollectionRealm(),
    var events: MarvelCollectionRealm? = MarvelCollectionRealm(),
    var urls: RealmList<UrlsRealm> = RealmList()
) : RealmObject()
