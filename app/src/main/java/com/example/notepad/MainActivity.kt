package com.example.notepad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.notepad.data.Note
import com.example.notepad.model.NavGraph
import com.example.notepad.screen.NoteScreen
import com.example.notepad.model.NoteViewModel
import com.example.notepad.ui.theme.NotePadTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.StateFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //val noteViewModel: NoteViewModel by viewModels<NoteViewModel>()
            val noteViewModel = hiltViewModel<NoteViewModel>()
            //NoteApp(noteViewModel = noteViewModel)
            NavGraph(noteViewModel = noteViewModel)
        }
    }
}

@Composable
fun NoteApp(
    navController:NavController,
    noteViewModel: NoteViewModel,
    noteList: StateFlow<List<Note>>
){
    MyApp {
        //val notesList = noteViewModel.getAllNotes()
        NoteScreen(Notes = noteList ,
            onAddNote = {
                noteViewModel.addNote(it)
            },
            //onRemoveNote = {
                //noteViewModel.removeNote(it) },
    navController = navController, noteviewModel = noteViewModel)

    }
}


@Composable
fun MyApp(content:@Composable ()->Unit){
    NotePadTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                content()
            }
        }
    }

}