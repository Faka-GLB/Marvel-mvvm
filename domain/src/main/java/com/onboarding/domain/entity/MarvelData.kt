package com.onboarding.domain.entity

data class MarvelData(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val character: List<MarvelCharacter>
)
