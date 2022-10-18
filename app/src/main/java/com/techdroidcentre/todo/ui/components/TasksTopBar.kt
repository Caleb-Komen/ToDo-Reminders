package com.techdroidcentre.todo.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.techdroidcentre.todo.R

@ExperimentalMaterial3Api
@Composable
fun TasksTopBar(
    showColourPickerDialog: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "Tasks")
        },
        actions = {
            ActionColourPicker(showColourPickerDialog = showColourPickerDialog)
        }
    )
}

@Composable
fun ActionColourPicker(
    showColourPickerDialog: () -> Unit
) {
    IconButton(onClick = showColourPickerDialog) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_palette_24),
            contentDescription = "Colour picker"
        )
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun TasksTopBarPreview() {
    TasksTopBar(showColourPickerDialog = {})
}