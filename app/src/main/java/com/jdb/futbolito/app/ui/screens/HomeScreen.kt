package com.jdb.futbolito.app.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import com.jdb.futbolito.utils.Constants
import com.jdb.futbolito.domain.model.Match

@Composable
fun HomeScreen(
    matches: List<Match>,
    onMatchClick: (Match) -> Unit,
    onCreate: () -> Unit
) {

    val matchesState = produceState(initialValue = emptyList(), producer = {
        val db = Firebase.firestore
        db.collection("partidos")
            // addSnapshotListener observa cambios en tiempo real.
            // Si solo quieres cargar los datos una vez, usa .get().await()
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    // Manejar el error, por ejemplo, logueándolo.
                    println("Listen failed: $e")
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    // Convierte los documentos a una lista de objetos Match
                    // y actualiza el estado.
                    value = snapshot.toObjects<Match>()
                }
            }

        // Esta línea es necesaria para que la corrutina de produceState no termine inmediatamente.
        // awaitClose se asegura de que el listener permanezca activo.
        awaitDispose {
            // Aquí puedes limpiar el listener si es necesario,
            // aunque en este caso no es estrictamente requerido.
        }
    })

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = onCreate) {
                Icon(Icons.Default.Add, contentDescription = Constants.CREATE_MATCH)
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
            .navigationBarsPadding()
    ) { paddingValues ->
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            Text("Matches", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))


            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(matchesState.value) { match ->
                    Card(
                        onClick = { onMatchClick(match) },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(match.location, style = MaterialTheme.typography.titleMedium)
                            Text("${match.date} • ${match.time}")
                            Text("Open Spots: ${match.openSpots}")
                        }
                    }
                }
            }

        }
    }

}