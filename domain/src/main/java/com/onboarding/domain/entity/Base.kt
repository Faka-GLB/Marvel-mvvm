package com.onboarding.domain.entity

data class Base(
    val code: Int,
    val status: String,
    val copyRight: String,
    val attributionText: String,
    val AttributionHTML: String,
    val etag: String,
    val marvelData: MarvelData,
)
