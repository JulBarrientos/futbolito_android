package com.jdb.futbolito.data.datasource

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import com.jdb.futbolito.domain.model.Match
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseDataSource(
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
) {
    fun getMatchesFlow(): Flow<List<Match>> = callbackFlow {
        val listener = db.collection("partidos")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                    return@addSnapshotListener
                }
                snapshot?.let {
                    trySend(it.toObjects<Match>())
                }
            }

        awaitClose { listener.remove() }
    }
}