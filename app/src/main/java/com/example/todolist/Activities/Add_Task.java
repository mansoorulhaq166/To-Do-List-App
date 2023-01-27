package com.example.todolist.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.todolist.AppDatabase;
import com.example.todolist.R;
import com.example.todolist.Interfaces.Task_Dao;
import com.example.todolist.Models.Tasks;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Add_Task extends AppCompatActivity {

    EditText textTitle, textDesc;
    DatePicker datePicker;
    Button addTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Objects.requireNonNull(getSupportActionBar()).hide();

        textTitle = findViewById(R.id.edit_title);
        textDesc = findViewById(R.id.edit_description);
        datePicker = findViewById(R.id.edit_last_date);
        addTask = findViewById(R.id.add_task);

        AwesomeValidation validation = new AwesomeValidation(ValidationStyle.COLORATION);
        validation.addValidation(this, R.id.edit_title, RegexTemplate.NOT_EMPTY, R.string.error);
        validation.addValidation(this, R.id.edit_description, RegexTemplate.NOT_EMPTY, R.string.error);


        addTask.setOnClickListener(view -> {
            if (validation.validate()) {
                AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                        "Tasks_Db").build();
                Executor executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Task_Dao task_dao = database.task_dao();

                        // perform database operation here
                        int day = datePicker.getDayOfMonth();
                        int month = datePicker.getMonth();
                        int year = datePicker.getYear();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, month, day);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        String dueDate = simpleDateFormat.format(calendar.getTime());
                        String title = textTitle.getText().toString();
                        String description = textDesc.getText().toString();

                        Tasks tasks = new Tasks(title, description, dueDate);
                        task_dao.insert_task(tasks);
                    }
                });

                textTitle.setText("");
                textDesc.setText("");

                Toast.makeText(Add_Task.this, "Task Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}