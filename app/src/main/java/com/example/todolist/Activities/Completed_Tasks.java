package com.example.todolist.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import com.example.todolist.Adapters.CompletedAdapter;
import com.example.todolist.AppDatabase;
import com.example.todolist.Interfaces.Task_Dao;
import com.example.todolist.Models.Tasks;
import com.example.todolist.R;

import java.util.List;
import java.util.Objects;

public class Completed_Tasks extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_tasks);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Completed Tasks");


        AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "Tasks_Db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        Task_Dao task_dao = database.task_dao();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Tasks> tasks = task_dao.getCompletedTask(true);

        CompletedAdapter completedAdapter = new CompletedAdapter(tasks);
        recyclerView.setAdapter(completedAdapter);


    }
}