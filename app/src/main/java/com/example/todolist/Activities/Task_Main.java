package com.example.todolist.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.todolist.Adapters.MyAdapter;
import com.example.todolist.AppDatabase;
import com.example.todolist.Interfaces.Task_Dao;
import com.example.todolist.Models.Tasks;
import com.example.todolist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Task_Main extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton addFab;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFab = findViewById(R.id.add_fab);
        getroomdata();

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Task_Main.this, Add_Task.class));
            }
        });
    }

    private void getroomdata() {
        AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "Tasks_Db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        Task_Dao task_dao = database.task_dao();
        recyclerView = findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Tasks> tasks = task_dao.getAllTask();

        MyAdapter.CheckboxCheckedChangeListener listener = new MyAdapter.CheckboxCheckedChangeListener() {
            @Override
            public void onCheckedChanged(int position, boolean isChecked) {
                if (isChecked) {
                    //imageButton.setVisibility(View.VISIBLE);
                    Toast.makeText(Task_Main.this, "Task Completed", Toast.LENGTH_SHORT).show();
                }
            }
        };
        MyAdapter adapter = new MyAdapter(listener, tasks);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        getroomdata();
    }
}