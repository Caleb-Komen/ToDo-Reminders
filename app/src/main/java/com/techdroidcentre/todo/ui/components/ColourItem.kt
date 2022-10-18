package com.techdroidcentre.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColourItem(
    colour: Int,
    onColourSelected: (Int) -> Unit,
    isSelected: Boolean,
    currentColour: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(width = 36.dp, height = 24.dp)
            .clip(RectangleShape)
            .background(Color(colour))
            .clickable {
                onColourSelected(colour)
            }
            .border(
                width = 2.dp,
                color = if (currentColour == colour) Color.DarkGray else Color.Transparent
            ),
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Check",
                modifier = Modifier.align(Alignment.Center),
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColourItemPreview() {
    ColourItem(Color(0xFFFF98DE).toArgb(), {}, true, Color(0xFFFF98DE).toArgb())
}