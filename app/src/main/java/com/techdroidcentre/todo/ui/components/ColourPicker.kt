package com.techdroidcentre.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.techdroidcentre.todo.ui.tasks.TasksViewModel
import com.techdroidcentre.todo.ui.util.colours

@Composable
fun ColourPicker(
    colours: List<Int>,
    closeDialog: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel
) {
    val currentColour = viewModel.uiState.value.colour
    var selectedColour by remember { mutableStateOf(currentColour) }

    ColourPicker(
        colours = colours,
        closeDialog = closeDialog,
        selectedColour = selectedColour,
        currentColour = currentColour,
        onColourSelected = {
            selectedColour = it
        },
        updateColour = {
            viewModel.updateColour(it)
        },
        modifier = modifier
    )
}

@Composable
fun ColourPicker(
    colours: List<Int>,
    closeDialog: () -> Unit,
    selectedColour: Int,
    currentColour: Int,
    onColourSelected: (Int) -> Unit,
    updateColour: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        title = {
            Text(text = "Choose a Colour")
        },
        text = {
            ColoursList(
                colours = colours,
                onColourSelected = onColourSelected,
                currentColour = currentColour
            )
        },
        onDismissRequest = {
            closeDialog()
        },
        confirmButton = {
            Button(
                onClick = {
                    if (selectedColour != currentColour) updateColour(selectedColour)
                    closeDialog()
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "OK",
                    modifier = Modifier
                        .background(Color.Transparent)
                )
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    closeDialog()
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Cancel",
                    modifier = Modifier
                        .background(Color.Transparent)
                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ColourPickerPreview() {
    ColourPicker(colours.map{ it.toArgb() }, {}, viewModel = hiltViewModel<TasksViewModel>())
}