package com.onboarding.data.database.entity

import com.onboarding.data.utils.ConstantUtil
import io.realm.RealmList
import io.realm.RealmObject

open class MarvelCollectionRealm(
    var available: Int = ConstantUtil.DEFAULT_INT_VALUE,
    var collection: String = ConstantUtil.DEFAULT_STRING_VALUE,
    var items: RealmList<CollectionItemRealm> = RealmList(),
    var returned: Int = ConstantUtil.DEFAULT_INT_VALUE
) : RealmObject()
