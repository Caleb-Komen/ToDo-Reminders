package com.techdroidcentre.todo.ui.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techdroidcentre.todo.ui.theme.ToDoTheme

@ExperimentalMaterial3Api
@Composable
fun TaskItem(
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isTitleFocused: Boolean,
    isContentFocused: Boolean,
    onTitleFocusChanged: (FocusState) -> Unit,
    onContentFocusChanged: (FocusState) -> Unit,
    taskExpanded: Boolean,
    dropDownExpanded: Boolean,
    priority: String,
    date: String,
    onDismissRequest: () -> Unit,
    onDropDownMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick = { /*TODO*/ },
        shape = ShapeDefaults.ExtraSmall.copy(CornerSize(2.dp)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TaskHeaderSection(
                title = title,
                onTitleChange = onTitleChange,
                checked = checked,
                onCheckedChange = onCheckedChange,
                isTitleFocused = isTitleFocused,
                onTitleFocusChanged = onTitleFocusChanged,
                taskExpanded = taskExpanded
            )
            TaskBodySection(
                content = content,
                onContentChange = onContentChange,
                isContentFocused = isContentFocused,
                onContentFocusChanged = onContentFocusChanged,
                priority = priority,
                date = date,
                dropDownExpanded = dropDownExpanded,
                onDismissRequest = onDismissRequest,
                onDropDownMenuClick = onDropDownMenuClick
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    ToDoTheme {
        TaskItem(
            title = "Jetpack Compose",
            content = "Finish compose codelab",
            onTitleChange = {},
            onContentChange = {},
            checked = false,
            onCheckedChange = {},
            isTitleFocused = false,
            isContentFocused = false,
            onTitleFocusChanged = {},
            onContentFocusChanged = {},
            taskExpanded = true,
            dropDownExpanded = true,
            priority = "None",
            date = "01/11/2022",
            onDismissRequest = {},
            onDropDownMenuClick = {}
        )
    }
}
