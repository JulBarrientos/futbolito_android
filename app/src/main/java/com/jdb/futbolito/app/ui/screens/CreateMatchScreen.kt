package com.jdb.futbolito.app.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun CreateMatchScreen(onCreate: () -> Unit) {

    var location by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Create Match", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date") })
        OutlinedTextField(value = time, onValueChange = { time = it }, label = { Text("Time") })

        Button(
            onClick = {
                val db = Firebase.firestore

                val matchData = hashMapOf(
                    "location" to location,
                    "date" to date,
                    "time" to time
                )
                db.collection("partidos")
                    .add(matchData)
                    .addOnSuccessListener {
                        onCreate()
                    }
                    .addOnFailureListener { exception ->
                        Log.e("error", "error")
                    }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Create")
        }
    }
}

@Composable
fun alert(){
    AlertDialog(
        title = {
            Text(text = "error")
        },
        text = {
            Text(text = "error")
        },
        onDismissRequest = {

        },
        confirmButton = {

        },
        dismissButton = {

        }
    )
}
