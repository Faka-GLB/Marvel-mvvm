package com.onboarding.domain.entity

data class MarvelCollection(
    val available: Int,
    val collection: String,
    val items: List<CollectionItem>,
    val returned: Int
)
