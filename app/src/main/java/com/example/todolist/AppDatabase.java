package com.example.todolist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todolist.Interfaces.Task_Dao;
import com.example.todolist.Models.Tasks;

@Database(entities = Tasks.class, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Task_Dao task_dao();
}
