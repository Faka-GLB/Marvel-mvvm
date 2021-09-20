package com.onboarding.data.database.entity

import com.onboarding.data.util.ConstantUtil
import io.realm.RealmObject

open class ThumbnailRealm(
    var path: String = ConstantUtil.DEFAULT_STRING_VALUE,
    var extension: String = ConstantUtil.DEFAULT_STRING_VALUE
) : RealmObject()
