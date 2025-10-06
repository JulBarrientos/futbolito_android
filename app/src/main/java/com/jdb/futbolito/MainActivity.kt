package com.jdb.futbolito

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jdb.futbolito.app.navigation.AppNavHost
import com.jdb.futbolito.ui.theme.FutbolitoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FutbolitoTheme {
                AppNavHost()
            }
        }
    }
}
