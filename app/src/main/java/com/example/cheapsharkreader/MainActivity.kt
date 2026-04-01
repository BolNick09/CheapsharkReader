package com.example.cheapsharkreader


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cheapsharkreader.presentation.navigation.AppNavGraph
import com.example.cheapsharkreader.ui.theme.CheapsharkReaderTheme


class MainActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheapsharkReaderTheme {
                AppNavGraph()
            }
        }


    }
}



