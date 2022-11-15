package com.techdroidcentre.todo.di

import android.content.Context
import androidx.room.Room
import com.techdroidcentre.todo.data.local.TaskDao
import com.techdroidcentre.todo.data.local.ToDoDatabase
import com.techdroidcentre.todo.data.local.ToDoListDao
import com.techdroidcentre.todo.data.repository.TasksRepository
import com.techdroidcentre.todo.data.repository.TasksRepositoryImpl
import com.techdroidcentre.todo.data.repository.ToDoListRepository
import com.techdroidcentre.todo.data.repository.ToDoListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java,
            ToDoDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideToDoListDao(database: ToDoDatabase): ToDoListDao {
        return database.toDoListDao
    }

    @Singleton
    @Provides
    fun provideTaskDao(database: ToDoDatabase): TaskDao {
        return database.taskDao
    }

    @Singleton
    @Provides
    fun provideToDoListRepository(toDoListDao: ToDoListDao): ToDoListRepository {
        return ToDoListRepositoryImpl(toDoListDao)
    }

    @Singleton
    @Provides
    fun provideTasksRepository(toDoListDao: ToDoListDao, taskDao: TaskDao): TasksRepository {
        return TasksRepositoryImpl(toDoListDao, taskDao)
    }
}