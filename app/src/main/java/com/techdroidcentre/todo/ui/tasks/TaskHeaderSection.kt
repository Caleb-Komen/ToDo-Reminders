package com.techdroidcentre.todo.ui.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techdroidcentre.todo.ui.theme.ToDoTheme

@ExperimentalMaterial3Api
@Composable
fun TaskHeaderSection(
    title: String,
    onTitleChange: (String) -> Unit,
    completed: Boolean,
    onCompletedChange: (Boolean) -> Unit,
    isTitleFocused: Boolean,
    onTitleFocusChanged: (FocusState) -> Unit,
    taskExpanded: Boolean,
    onExpandTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = null, modifier = Modifier.size(16.dp))
        Checkbox(checked = completed, onCheckedChange = onCompletedChange)
        if (completed) {
            Text(
                text = title,
                textDecoration = TextDecoration.LineThrough,
                modifier = Modifier.weight(1f)
            )
        } else {
            TaskTitleTextField(
                value = title,
                onValueChange = onTitleChange,
                isFocused = isTitleFocused,
                onFocusChanged = onTitleFocusChanged,
                modifier = Modifier.weight(1f)
            )
        }
        IconButton(onClick = onExpandTask) {
            if (taskExpanded)
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = null
                )
            else
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun TaskTitleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isFocused: Boolean,
    onFocusChanged: (FocusState) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .widthIn(min = 48.dp)
            .onFocusChanged {
                onFocusChanged(it)
            }
            .border(
                width = 1.dp,
                color = if (isFocused) Color.Yellow else Color.Transparent,
                shape = MaterialTheme.shapes.small
            )
            .background(Color.Transparent, MaterialTheme.shapes.small),
        shape = ShapeDefaults.Small,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun TaskHeaderSectionPreview() {
    ToDoTheme {
        TaskHeaderSection("Hello".repeat(12), {}, true, {}, false, {}, false, {})
    }
}
