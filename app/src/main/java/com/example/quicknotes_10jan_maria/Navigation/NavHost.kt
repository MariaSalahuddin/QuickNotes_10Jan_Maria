package com.example.quicknotes_10jan_maria.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quicknotes_10jan_maria.Views.HomeScreenNotes
import com.example.quicknotes_10jan_maria.Views.AddAndEditNote
import com.example.quicknotes_10jan_maria.DataClass.Note

@Composable


fun MyNavigationGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val notes = remember { mutableStateListOf<Note>() }
    notes.add(Note(id=1, title = "Assignment 3", content = "Should be submitted before sunday it is the deadline, need to add new component before submitting"))
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreenNotes(
                notes = notes,
                onAddNote = { navController.navigate("note/0") },
                onViewNote = {  note -> navController.navigate("note/${note.id}") })
        }
        composable(
            "note/{noteId}", arguments = listOf(navArgument("noteId")
            { type = NavType.StringType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toInt()
            if (noteId == 0) {
                AddAndEditNote(
                    note = Note(id=0, title = "", content = ""),
                    onSave = {note ->
                        notes.add(note)
                        navController.navigateUp()
                    },
                    onCancel = { navController.navigateUp() });
            } else {
                val note = notes.find { it.id == noteId }
                if (note != null) {
                    AddAndEditNote(
                        note = note,
                        onSave = {
                            navController.navigateUp()
                        },
                        onCancel = { navController.navigateUp() });
                }
            }
        }
    }
}