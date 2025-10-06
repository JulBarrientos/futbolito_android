package com.jdb.futbolito.domain.model

data class Match(
    val location: String,
    val date: String,
    val time: String,
    val openSpots: Int
)