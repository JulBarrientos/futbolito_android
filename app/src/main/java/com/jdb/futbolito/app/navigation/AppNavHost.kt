package com.jdb.futbolito.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jdb.futbolito.app.ui.screens.ConfirmationScreen
import com.jdb.futbolito.app.ui.screens.CreateMatchScreen
import com.jdb.futbolito.app.ui.screens.HomeScreen
import com.jdb.futbolito.app.ui.screens.MatchDetailScreen
import com.jdb.futbolito.domain.model.Match
import kotlin.collections.plus

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    // Lista de partidos dummy
    var matches by remember { mutableStateOf(listOf<Match>()) }

    NavHost(navController = navController, startDestination = "list") {

        // Crear Partido
        composable("create") {
            CreateMatchScreen(
                onCreate = {
                    // Agregamos partido ficticio (ejemplo)
                    val newMatch = Match("Local Park", "2025-09-18", "18:00", 5)
                    matches = matches + newMatch
                    navController.navigate("list")
                }
            )
        }

        // Lista de Partidos
        composable("list") {
            HomeScreen(
                matches = matches,
                onMatchClick = { match ->
                    // Pasar partido seleccionado como argumento simple (posición en lista)
                    val index = matches.indexOf(match)
                    navController.navigate("details/$index")
                },
                onCreate = {
                    navController.navigate("create")
                }
            )
        }

        // Detalles de Partido
        composable(
            route = "details/{matchIndex}",
            arguments = listOf(navArgument("matchIndex") { type = NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("matchIndex") ?: 0
            val match = matches[index]

            MatchDetailScreen(
                match = match,
                onJoin = {
                    navController.navigate("confirmation")
                }
            )
        }

        // Confirmación de Unión
        composable("confirmation") {
            ConfirmationScreen(
                onOk = {
                    navController.navigate("list") {
                        popUpTo("list") { inclusive = true }
                    }
                }
            )
        }
    }
}