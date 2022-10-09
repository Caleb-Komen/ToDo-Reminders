package com.techdroidcentre.todo.data.local

import androidx.room.TypeConverter
import com.techdroidcentre.todo.data.util.Priority

class Converter {
    @TypeConverter
    fun fromPriority(priority: Priority) = priority.name

    @TypeConverter
    fun toPriority(priority: String) = Priority.valueOf(priority)
}