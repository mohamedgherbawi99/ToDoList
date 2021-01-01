package com.todolist.todolist.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.todolist.todolist.R;

import java.util.ArrayList;
import java.util.HashMap;

import adapter.ListAdapter;
import adapter.ToDoLists;
import utils.AuthUtil;
import utils.FirestoreUtil;

public class MainActivity extends AppCompatActivity {


    RecyclerView toDoListRecyclerView ;
    ListAdapter listAdapter;
    AppCompatTextView logOut;
    AppCompatEditText addEditText;
    ImageView backMain, add_img;
    String itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        logOut.setOnClickListener(v -> {
            AuthUtil.getInstance().logOut(this);
        });
        FirestoreUtil.getInstance().getFromList("lists",arrayList -> {
            listAdapter = new ListAdapter(this ,arrayList);
            toDoListRecyclerView.setAdapter(listAdapter);
            listAdapter.notifyDataSetChanged();
        });

        backMain.setOnClickListener(v ->{
            this.finish();
        });

        add_img.setOnClickListener(v ->{
            itemList= addEditText.getText().toString();
            if (itemList.matches("")){
                addEditText.setError("Set item list name");
            }else {
                FirestoreUtil.getInstance().addToList(itemList,new ArrayList<>());
                addEditText.getText().clear();
                Snackbar.make(this.findViewById(android.R.id.content),
                 "add sucess", Snackbar.LENGTH_LONG).show();
                listAdapter.notifyDataSetChanged();
                toDoListRecyclerView.invalidate();
            }
        });


//        toDoListRecyclerView = findViewById(R.id.toDoListRecyclerView);

//        toDoLists.add(new ToDoLists("Assinment",3));
//        toDoLists.add(new ToDoLists("Art",6));
//        toDoLists.add(new ToDoLists("Number",7));
//        toDoLists.add(new ToDoLists("Ways",8));

//        toDoListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        listAdapter = new ListAdapter(this ,toDoLists );
//        toDoListRecyclerView.setAdapter(listAdapter);
    }

    private void init(){
        logOut= findViewById(R.id.logOut);
        backMain= findViewById(R.id.backMain);
        add_img = findViewById(R.id.add_img);
        addEditText= findViewById(R.id.addEditText);
        toDoListRecyclerView = findViewById(R.id.toDoListRecyclerView);
        toDoListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}