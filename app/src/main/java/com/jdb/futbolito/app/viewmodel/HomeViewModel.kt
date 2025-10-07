package com.jdb.futbolito.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jdb.futbolito.domain.model.Match
import com.jdb.futbolito.domain.usecase.GetMatchesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMatchesUseCase: GetMatchesUseCase
) : ViewModel() {

    private val _matches = MutableStateFlow<List<Match>>(emptyList())
    val matches: StateFlow<List<Match>> = _matches

    init {
        viewModelScope.launch {
            getMatchesUseCase()
                .onEach { _matches.value = it }
                .catch { e -> println("Error loading matches: $e") }
                .collect {}
        }
    }
}