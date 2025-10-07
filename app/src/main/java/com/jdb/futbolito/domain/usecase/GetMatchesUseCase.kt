package com.jdb.futbolito.domain.usecase

import com.jdb.futbolito.domain.model.Match
import com.jdb.futbolito.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow

class GetMatchesUseCase(
    private val repository: MatchRepository
) {
    operator fun invoke(): Flow<List<Match>> = repository.getMatches()
}