package com.example.notepad.model

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notepad.NoteApp
import com.example.notepad.screen.DetailScreen


@Composable
fun NavGraph(noteViewModel: NoteViewModel){
    val navController  = rememberNavController()

    NavHost(navController = navController,
            startDestination = "HomeScreen" ){
        composable("HomeScreen"){
            NoteApp(navController = navController, noteViewModel = noteViewModel, noteList = noteViewModel.noteList)
        }
        composable("DetailScreen/{name}", arguments = listOf(
            navArgument(name = "name"){type = NavType.StringType}
        )){backStack->
            DetailScreen(navController = navController,backStack.arguments?.getString("name"), noteViewModel = noteViewModel)
        }
    }

}

