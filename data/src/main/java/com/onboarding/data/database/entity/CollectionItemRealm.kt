package com.onboarding.data.database.entity

import com.onboarding.data.util.ConstantUtil
import io.realm.RealmObject

open class CollectionItemRealm(
    var resourceURI: String = ConstantUtil.DEFAULT_STRING_VALUE,
    var name: String = ConstantUtil.DEFAULT_STRING_VALUE,
    var type: String = ConstantUtil.DEFAULT_STRING_VALUE
) : RealmObject()
