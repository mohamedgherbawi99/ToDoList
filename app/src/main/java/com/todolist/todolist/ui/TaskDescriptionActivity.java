package com.todolist.todolist.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;

import com.todolist.todolist.R;

import adapter.Task;
import adapter.ToDoLists;

public class TaskDescriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_description);

        Intent intent = getIntent();
        Task task = (Task) intent.getSerializableExtra("task_obj");

        AppCompatTextView taskdec = findViewById(R.id.taskdec);
        AppCompatTextView taskname = findViewById(R.id.taskname);

        taskdec.setText(task.getDescription());
        taskname.setText(task.getTaskName());
    }
}