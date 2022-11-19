package com.techdroidcentre.todo.data.local

import androidx.room.*
import com.techdroidcentre.todo.data.local.entities.ToDoListEntity
import com.techdroidcentre.todo.data.local.entities.ToDoListWithTasksEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoListDao {
    @Transaction
    @Query("SELECT * FROM todo_list")
    fun getAllToDoList(): Flow<List<ToDoListWithTasksEntity>>

    @Query("SELECT * FROM todo_list WHERE id = :id")
    fun getToDoList(id: Long): Flow<ToDoListEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToDoList(toDoList: ToDoListEntity)

    @Update
    suspend fun updateToDoList(todoList: ToDoListEntity)

    @Query("DELETE FROM todo_list WHERE id = :id")
    suspend fun deleteToDoList(id: Long)
}