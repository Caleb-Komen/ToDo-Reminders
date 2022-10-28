package com.techdroidcentre.todo.data.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.techdroidcentre.todo.util.createNotification

const val KEY_TASK_TITLE = "task_title"

class ReminderWorker(val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val content = inputData.getString(KEY_TASK_TITLE).toString()
        createNotification(context, content)
        return Result.success()
    }
}