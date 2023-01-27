package com.example.todolist.Interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.Models.Tasks;

import java.util.List;

@Dao
public interface Task_Dao {
    @Insert
    void insert_task(Tasks tasks);

    @Update
    void update_tasks(Tasks tasks);

    @Delete
    void delete_tasks(Tasks tasks);

    @Query("Select * from Tasks")
    List<Tasks> getAllTask();

}
