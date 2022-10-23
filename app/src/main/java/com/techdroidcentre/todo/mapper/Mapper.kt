package com.techdroidcentre.todo.mapper

import com.techdroidcentre.todo.data.local.entities.TaskEntity
import com.techdroidcentre.todo.data.local.entities.ToDoListEntity
import com.techdroidcentre.todo.data.local.entities.ToDoListWithTasksEntity
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.model.ToDoList

fun TaskEntity.toDomainModel(): Task {
    return Task(
        id = id,
        title = title,
        content = content,
        dueDate = dueDate,
        priority = priority,
        isComplete = isComplete
    )
}

fun ToDoListWithTasksEntity.toDomainModel(): ToDoList {
    return ToDoList(
        id = toDoListEntity.id,
        title = toDoListEntity.title,
        colour = toDoListEntity.colour,
        tasks = tasksEntity.map { it.toDomainModel() }
    )
}

fun ToDoListEntity.toDomainModel(): ToDoList {
    return ToDoList(
        id = id,
        title = title,
        colour = colour,
        tasks = emptyList()
    )
}

fun Task.toEntity(todoListId: Long): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        content = content,
        dueDate = dueDate,
        priority = priority,
        isComplete = isComplete,
        toDoListId = todoListId
    )
}

fun ToDoList.toEntity(): ToDoListEntity {
    return ToDoListEntity(id = id, title = title, colour = colour)
}