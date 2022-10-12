package com.techdroidcentre.todo.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalMaterial3Api
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(text = "ToDo")
        },
        actions = {
            ActionNewToDoList()
        }
    )
}

@Composable
fun ActionNewToDoList() {
    IconButton(onClick = {  }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add new ToDo list"
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun TopBarPreview() {
    TopBar()
}