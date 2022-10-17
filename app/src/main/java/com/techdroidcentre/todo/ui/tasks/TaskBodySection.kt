package com.techdroidcentre.todo.ui.tasks

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techdroidcentre.todo.ui.theme.ToDoTheme

@ExperimentalMaterial3Api
@Composable
fun TaskBodySection(
    content: String,
    onContentChange: (String) -> Unit,
    isContentFocused: Boolean,
    onContentFocusChanged: (FocusState) -> Unit,
    priority: String,
    date: String,
    dropDownExpanded: Boolean,
    onDismissRequest: () -> Unit,
    onDropDownMenuClick: () -> Unit,
    onDropDownMenuItemSelected: (String) -> Unit,
    onTaskDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Divider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PriorityDropDownMenu(
                priority = priority,
                dropDownExpanded = dropDownExpanded,
                onDismissRequest = onDismissRequest,
                onDropDownMenuClick = onDropDownMenuClick,
                onDropDownMenuItemSelected = onDropDownMenuItemSelected
            )
            Spacer(modifier = Modifier.width(8.dp))
            DateAndTime(date = date)
        }
        Spacer(modifier = Modifier.height(8.dp))
        TaskContentTextField(
            value = content,
            onValueChange = onContentChange,
            isFocused = isContentFocused,
            onFocusChanged = onContentFocusChanged
        )
        Button(
            onClick = onTaskDelete,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC01C28)),
            shape = ShapeDefaults.Small,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Delete")
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun TaskContentTextField(
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
            .fillMaxWidth()
            .height(156.dp)
            .onFocusChanged {
                onFocusChanged(it)
            }
            .border(
                width = 1.dp,
                color = if (isFocused) Color.Yellow else Color.Gray
            ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle.Default.copy(textAlign = TextAlign.Start)
    )
}

@Composable
fun PriorityDropDownMenu(
    priority: String,
    dropDownExpanded: Boolean,
    onDismissRequest: () -> Unit,
    onDropDownMenuClick: () -> Unit,
    onDropDownMenuItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Priority:")
        Spacer(modifier = Modifier.width(4.dp))
        Row(
            modifier = Modifier
                .clickable(onClick = onDropDownMenuClick)
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = priority
                )
                DropdownMenu(
                    expanded = dropDownExpanded,
                    onDismissRequest = onDismissRequest
                ) {
                    priorities.forEach {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = { onDropDownMenuItemSelected(it.uppercase()) }
                        )
                    }
                }
            }
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Open drop-down menu"
            )
        }
    }
}

@Composable
fun DateAndTime(
    date: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Due Date:")
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = date,
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = MaterialTheme.shapes.extraSmall
                )
                .padding(8.dp)
        )
    }
}

val priorities = arrayOf("High", "Medium", "Low", "None")

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun TaskBodySectionPreview() {
    ToDoTheme {
        TaskBodySection(
            "Task content", {}, false, {},
            "None", "01/01/2022", false,
            {}, {}, {}, {}, {}
        )
    }
}