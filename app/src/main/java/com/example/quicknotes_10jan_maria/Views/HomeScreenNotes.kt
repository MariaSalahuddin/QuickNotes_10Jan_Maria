package com.example.quicknotes_10jan_maria.Views
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicknotes_10jan_maria.DataClass.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenNotes(notes: List<Note>,
                    onAddNote: () -> Unit,
                    onViewNote: (Note) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Quick Notes")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddNote) {
                Text("+")
            }
        }
    ) {

        Column(modifier = Modifier.fillMaxSize().padding(it)) {
            if (notes.isEmpty()) {
                Text(text = "No items to display",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 20.sp
               )
            } else {
                LazyColumn() {
                    items(notes.size) { index ->
                        NoteCard(note = notes[index], onClick = { onViewNote(notes[index]) })
                    }
                }
            }
        }
    }

}
@Composable
fun NoteCard(note: Note, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, fontSize = 20.sp)
            Text(
                text = note.content.take(50),
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}