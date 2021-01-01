package com.todolist.todolist.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.todolist.todolist.R;

import java.util.ArrayList;

import adapter.ListAdapter;
import adapter.Task;
import adapter.TaskAdapter;
import adapter.ToDoLists;
import utils.FirestoreUtil;

public class DetailsActivity extends AppCompatActivity {

    private String TAG= "DetailsActivity";
    ArrayList<Task> tasks = new ArrayList<>();
    RecyclerView tasksRecyclerView ;
    TaskAdapter taskAdapter;
    ImageView addTask;
    AppCompatEditText taskName, taskDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
        Intent intent = getIntent();
        ToDoLists toDoLists = (ToDoLists) intent.getSerializableExtra("list_obj");
        Log.d(TAG,toDoLists.getNameList());

        FirestoreUtil.getInstance().getFromDocument("lists", toDoLists.getNameList()
                ,arrayList -> {
                    this.tasks = arrayList;
                    taskAdapter = new TaskAdapter(this ,arrayList);
                    tasksRecyclerView.setAdapter(taskAdapter);
                });

        addTask.setOnClickListener(v->{
            String taskNameAdd= taskName.getText().toString();
            String taskdesAdd= taskDesc.getText().toString();
            Task task = new Task(taskNameAdd,taskdesAdd,true);
            this.tasks.add(task);
            FirestoreUtil.getInstance().update(tasks, toDoLists.getNameList());
            taskAdapter.notifyDataSetChanged();
        });




//        tasksRecyclerView= findViewById(R.id.tasksRC);
//
//        tasks.add(new Task("Assinment",true));
//        tasks.add(new Task("Art",true));
//        tasks.add(new Task("Number",true));
//        tasks.add(new Task("Ways",true));
//
//        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        taskAdapter = new TaskAdapter(this ,tasks);
//        tasksRecyclerView.setAdapter(taskAdapter);
//        taskAdapter.notifyDataSetChanged();



    }

    private void init() {
        addTask = findViewById(R.id.addTask);
        tasksRecyclerView= findViewById(R.id.tasksRC);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskName = findViewById(R.id.taskName);
        taskDesc = findViewById(R.id.taskDesc);
    }
}