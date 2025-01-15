package com.example.quicknotes_10jan_maria.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicknotes_10jan_maria.DataClass.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAndEditNote(
    note: Note,
    onSave: (Note) -> Unit,
    onCancel: () -> Unit,

    ) {

    val title = remember { mutableStateOf(note.title) }
    val content = remember { mutableStateOf(note.content) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (note.id == 0) "Add New Note" else "Edit Note",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        TextField(
            value = title.value,
            onValueChange = { title.value = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = content.value,
            onValueChange = { content.value = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = onCancel) {
                Text("Cancel")
            }
            Button(onClick = {
                if (note.id == 0) {
                    if (title.value.isNotBlank() && content.value.isNotBlank()) {
                        onSave(
                            Note(
                                id = System.currentTimeMillis().toInt(),
                                title = title.value,
                                content = content.value
                            )
                        )
                    }
                } else {
                    note.title = title.value
                    note.content = content.value
                    onSave(note)
                }
            }) {
                Text("Save")
            }
        }
    }
}