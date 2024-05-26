package com.example.notepad.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

data class DropDownItem(
    val text:String= "Delete"
)

@Composable
fun PersonItem(
    personName:String,
    dropDownItem: List<String>,
    modifier: Modifier = Modifier,
    onItemClick:(String)->Unit
){
    var isContextMenuVisible by rememberSaveable {
            mutableStateOf(false)
    }

    var pressOffset by remember{
        mutableStateOf(DpOffset.Zero)
    }

    var itemHeight by remember{
        mutableStateOf(0.dp)
    }

    val dencity = LocalDensity.current

    Card(elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.onSizeChanged { itemHeight = with(dencity){it.height.toDp()} },
        ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .pointerInput(true) {
                detectTapGestures(
                    onLongPress = {
                        isContextMenuVisible = true
                        pressOffset = DpOffset(it.x.toDp(), it.y.toDp())
                    }
                )
            }
            .padding(16.dp)) {
            Text(text = personName)
        }
        DropdownMenu(expanded = isContextMenuVisible,
            onDismissRequest = {
            isContextMenuVisible = false
        }) {
            dropDownItem.forEach{item->
              DropdownMenuItem(text = { item }, onClick = {
                  onItemClick(item)
                  isContextMenuVisible = false
              })

            }
        }
    }
}