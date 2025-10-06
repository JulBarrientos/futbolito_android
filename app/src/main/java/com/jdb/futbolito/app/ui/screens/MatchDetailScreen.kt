package com.jdb.futbolito.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jdb.futbolito.domain.model.Match

@Composable
fun MatchDetailScreen(match: Match, onJoin: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Match Details", style = MaterialTheme.typography.headlineMedium)
        Text("Location: ${match.location}")
        Text("Date: ${match.date}")
        Text("Time: ${match.time}")
        Text("Open Spots: ${match.openSpots}")

        Button(
            onClick = { onJoin() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Join Match")
        }
    }
}