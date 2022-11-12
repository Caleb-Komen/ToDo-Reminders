package com.techdroidcentre.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techdroidcentre.todo.ui.home.ToDoTabState

@Composable
fun ToDoTabRow(
    tabState: ToDoTabState,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    TabRow(
        selectedTabIndex = tabState.currentIndex,
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
        tabState.titles.forEachIndexed { index, title ->
            TabItem(
                selected = index == tabState.currentIndex,
                text = title,
                onClick = { onTabSelected(index) }
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
                color = if (selected) Color.LightGray else MaterialTheme.colorScheme.surface
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
    ToDoTabRow(
        ToDoTabState(
            titles = listOf("Lists", "Scheduled", "Today"),
            currentIndex = 0
        ), {})
}

@Preview
@Composable
fun TabItemPreview() {
    TabItem(selected = false, text = "Lists", {})
}