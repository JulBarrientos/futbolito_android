package com.jdb.futbolito.data.repository

import com.jdb.futbolito.data.datasource.FirebaseDataSource
import com.jdb.futbolito.domain.model.Match
import com.jdb.futbolito.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow

class MatchRepositoryImpl(
    private val remote: FirebaseDataSource
): MatchRepository {
    override fun getMatches(): Flow<List<Match>> = remote.getMatchesFlow()
}


