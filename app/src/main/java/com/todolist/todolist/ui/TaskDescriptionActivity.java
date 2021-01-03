package com.todolist.todolist.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.todolist.todolist.R;

import adapter.Task;
import adapter.ToDoLists;

public class TaskDescriptionActivity extends AppCompatActivity {

    ImageView finishTaskDes;
    AppCompatTextView taskdec, taskname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_description);

        init();

        finishTaskDes.setOnClickListener(v->{
            this.finish();
        });
        Intent intent = getIntent();
        Task task = (Task) intent.getSerializableExtra("task_obj");

        taskdec.setText(task.getDescription());
        taskname.setText(task.getTaskName());
    }

    private void init(){
        finishTaskDes=findViewById(R.id.finishTaskDes);
        taskdec = findViewById(R.id.taskdec);
        taskname = findViewById(R.id.taskname);
    }
}