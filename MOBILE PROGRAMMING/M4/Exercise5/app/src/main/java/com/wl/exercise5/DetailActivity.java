package com.wl.exercise5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ToDoDetailFragment frag = (ToDoDetailFragment) getSupportFragmentManager().findFragmentById(R.id.detail_frag);
        int toDoId = (int) getIntent().getExtras().get(EXTRA_TODO_ID);
        frag.setToDoId(toDoId);
    }




    public void onClickDeleteTask(View view){

    }
}