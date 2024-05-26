package com.example.notepad.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notepad.data.Note
import com.example.notepad.model.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInputText(modifier: Modifier = Modifier,
                          text:String,
                          label:@Composable ()->Unit,
                          maxLine:Int = 1,
                          onTextChage:(String)->Unit,
                         // onItemAction:()->Unit = {},
                          keybordAction:ImeAction,

                          ){
   OutlinedTextField(modifier = modifier
       .height(60.dp)
       .background(shape = CircleShape, color = Color.Transparent),
        value = text,
        onValueChange = {it->
            onTextChage(it)
                        },
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent),
        label = {label()},
        shape = CircleShape,
        maxLines = maxLine,
        keyboardOptions  =  KeyboardOptions(imeAction = keybordAction)
    )
}

@Composable
fun SavedNote(modifier:Modifier = Modifier,note:Note,onNoteClicked:()->Unit,noteViewModel:NoteViewModel){
    var isContextMenuVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var pressOffset by remember{
        mutableStateOf(DpOffset.Zero)
    }

    var itemHeight by remember{
        mutableStateOf(0.dp)
    }
    var dropDownItem = listOf<String>("Delete")

    Card(modifier = modifier
        .padding(top = 10.dp)
        .fillMaxWidth()
        .wrapContentHeight()
        .pointerInput(true) {
            detectTapGestures(
                onLongPress = {
                    isContextMenuVisible = true
                    pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                }
            )
        }
       // .clickable { onNoteClicked()},
       // shape = RoundedCornerShape(20.dp),
        ,elevation = CardDefaults.cardElevation(50.dp)) {
        Column(modifier = Modifier
            .fillMaxSize()
           // .clickable { onNoteClicked() },
                //verticalArrangement = Arrangement.SpaceEvenly,
              ,  horizontalAlignment = Alignment.CenterHorizontally) {
                Row(modifier = Modifier.fillMaxWidth().padding(end = 20.dp, top = 20.dp), horizontalArrangement = Arrangement.End) {
                    Icon(imageVector = Icons.Filled.ArrowForward, contentDescription =null, modifier = Modifier.clickable { onNoteClicked() } )
                }
                Text(text = "Tital: ${note.title}")
                Divider(modifier = Modifier.padding(10.dp), color = Color.Cyan)
                Text(text = "Description: ${note.description}",modifier = Modifier.padding(10.dp))
        }
        DropdownMenu(expanded = isContextMenuVisible,
            onDismissRequest = {
                isContextMenuVisible = false
            }) {
            DropdownMenuItem(text = { Text(text = "Delete") }, onClick = {
                    noteViewModel.removeNote(note)
                    isContextMenuVisible = false
                }, leadingIcon = {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = null, tint = Color.Red)
            })


        }


    }
}



