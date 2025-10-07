package com.jdb.futbolito.domain.repository

import com.jdb.futbolito.domain.model.Match
import kotlinx.coroutines.flow.Flow


interface MatchRepository {
    fun getMatches(): Flow<List<Match>>
}