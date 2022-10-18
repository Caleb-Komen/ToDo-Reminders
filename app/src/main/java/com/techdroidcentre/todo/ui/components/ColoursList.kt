package com.techdroidcentre.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techdroidcentre.todo.ui.util.colours

@Composable
fun ColoursList(
    colours: List<Int>,
    onColourSelected: (Int) -> Unit,
    currentColour: Int,
    modifier: Modifier = Modifier
) {
    var colourSelected by remember { mutableStateOf(currentColour) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        contentPadding = PaddingValues(12.dp),
        modifier = modifier
            .background(Color.Transparent)
            .width(200.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = colours) { colour ->
            ColourItem(
                colour = colour,
                onColourSelected = {
                    colourSelected = it
                    onColourSelected(it)
                },
                isSelected = colourSelected == colour,
                currentColour = currentColour,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColoursListPreview() {
    ColoursList(colours.map { it.toArgb() }, {}, colours[0].toArgb())
}