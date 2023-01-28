package com.example.todolist.Interfaces;

import androidx.room.Dao;
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
    void update(Tasks tasks);

    @Query("UPDATE Tasks SET is_check = :is_check where id = :pid")
    int updateTask(boolean is_check, int pid);

    @Query("Delete from Tasks where id = :pid")
    int delete_task(int pid);

    @Query("Select * from Tasks")
    List<Tasks> getAllTask();

    @Query("Select * from Tasks where is_check = :is_check")
    List<Tasks> getCompletedTask(boolean is_check);

    @Query("Select * from Tasks where is_check = :is_check")
    List<Tasks> getUnCompletedTask(boolean is_check);
}
