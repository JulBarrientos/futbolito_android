package com.jdb.futbolito.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Match(
    val location: String = "",
    val date: String = "",
    val time: String = "",
    val openSpots: Int = 0
)