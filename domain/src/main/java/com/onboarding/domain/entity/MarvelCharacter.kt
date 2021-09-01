package com.onboarding.domain.entity

data class MarvelCharacter(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: Thumbnail,
    val characterURI: String,
    val comics: MarvelCollection,
    val series: MarvelCollection,
    val stories: MarvelCollection,
    val events: MarvelCollection,
    val urls: List<Urls>
)
