package com.techdroidcentre.todo.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.techdroidcentre.todo.data.local.ToDoDatabase
import org.junit.After
import org.junit.Before

open class BaseTest {
    lateinit var database: ToDoDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            ToDoDatabase::class.java
        ).build()
    }

    @After
    fun closeDb() {
        database.close()
    }
}