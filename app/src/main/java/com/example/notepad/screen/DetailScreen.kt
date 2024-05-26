package com.example.notepad.screen


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notepad.model.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController,name:String?,noteViewModel: NoteViewModel){
    val listItem = noteViewModel.noteList.collectAsState().value//because noteViewModel.noteList is type of Flow which is a flow of data we have to collect it first.
    val DetailScreenNote = listItem.filter { it->
        it.title==name
    }


    Column(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {TopAppBar(
                title = { Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start) {
                            Icon(imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Arrow Back",
                                modifier = Modifier.clickable {
                                    //navController.navigate(route = "HomeScreen")
                                    navController.popBackStack()
                                })
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = DetailScreenNote[0].title)
                            Spacer(modifier = Modifier.width(200.dp))
//                            )
                }}
            ) }
        ) {it->
            Column(modifier = Modifier
                .padding(it)
                .fillMaxSize()) {
                Text(text =  DetailScreenNote[0].description, fontSize = 20.sp)

            }

        }

    }
}