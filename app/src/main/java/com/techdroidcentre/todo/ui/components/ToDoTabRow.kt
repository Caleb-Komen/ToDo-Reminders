package com.techdroidcentre.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techdroidcentre.todo.ui.Screen
import com.techdroidcentre.todo.ui.screens

@Composable
fun ToDoTabRow(
    selectedTabIndex: Int,
    screens: List<Screen>,
    onTabSelected: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {

    TabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = {
            TabRowDefaults.Indicator(color = Color.Transparent)
        },
        contentColor = Color.Black,
        divider = { Divider(thickness = 0.dp) },
        modifier = modifier
            .height(56.dp)
            .padding(horizontal = 16.dp)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = MaterialTheme.shapes.small
            )
            .clip(shape = MaterialTheme.shapes.small)
    ) {
        screens.forEachIndexed { index, screen ->
            TabItem(
                selected = index == selectedTabIndex,
                text = screen.title,
                onClick = { onTabSelected(screen) }
            )
        }
    }
}

@Composable
fun TabItem(
    selected: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Tab(
        selected = selected,
        onClick = onClick,
        modifier = modifier
            .height(56.dp)
            .background(
                color = if (selected) Color.LightGray else MaterialTheme.colorScheme.background
            )
            .border(
                width = 1.dp,
                color = Color.LightGray
            )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(text = text)
        }
    }
}

@Preview
@Composable
fun ToDoTabRowPreview() {
    ToDoTabRow(0, screens, {})
}

@Preview
@Composable
fun TabItemPreview() {
    TabItem(selected = false, text = "Lists", {})
}