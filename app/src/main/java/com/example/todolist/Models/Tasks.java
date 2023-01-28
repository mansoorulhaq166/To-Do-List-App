package com.example.todolist.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "due_date")
    public String due_date;

    @ColumnInfo(name = "is_check", defaultValue = "false")
    public boolean is_check;

    public Tasks(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.due_date = date;
    }

    public Tasks() {

    }

    public void setIs_check(boolean is_check) {
        this.is_check = is_check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIs_check() {
        return is_check;
    }

    public void setIs_check(Boolean is_check) {
        this.is_check = is_check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getDescription() {
        return description;
    }

    public String getDue_date() {
        return due_date;
    }
}
