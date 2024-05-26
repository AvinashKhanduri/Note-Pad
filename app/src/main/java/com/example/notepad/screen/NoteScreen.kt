package com.example.notepad.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notepad.R
import com.example.notepad.components.NoteInputText
import com.example.notepad.components.SavedNote
import com.example.notepad.data.Note
import com.example.notepad.model.NoteViewModel
import kotlinx.coroutines.flow.StateFlow
//


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    navController: NavController,
    Notes: StateFlow<List<Note>>,
    onAddNote: (Note)->Unit,
    //onRemoveNote: (Note)->Unit,
    noteviewModel:NoteViewModel
){
    var tital by remember{
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val notes_list = Notes.collectAsState().value
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(5.dp))
                Text(text  = stringResource(id = R.string.app_name), fontWeight = FontWeight.ExtraBold, color = Color.Black)
            }
        },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFDADFE3))
        )
        //content
        Spacer(modifier = Modifier.height(10.dp))
        NoteInputText( text =tital ,
            label = { Text(text = "Tital")},
            onTextChage = {
                    tital = it

            },
            maxLine = 2,
            keybordAction = ImeAction.Done)
        
        Spacer(modifier = Modifier.height(10.dp))
        NoteInputText(text = description,
            onTextChage = {
                    description = it
            },
            label = { Text(text = "Description")},
            keybordAction = ImeAction.Done)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            if(tital.isNotEmpty() && description.isNotEmpty()){
                // save/add to the list
                onAddNote(Note(title = tital, description = description))
                 tital = ""
                description = ""
                Toast.makeText(context,"Note Added",Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Save")
        }
        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes_list){note->
                SavedNote(note = note, onNoteClicked = {
                   // onRemoveNote(note)
                    navController.navigate(route = "DetailScreen/${note.title}")

                }, noteViewModel = noteviewModel)
                
            }
        }


    }

}


