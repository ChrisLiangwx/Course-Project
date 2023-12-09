package com.wl.exercise5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements ToDoListFragment.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id){
        View fragmentContainer = findViewById(R.id.fragment_container);
        if(fragmentContainer != null){
            //dynamically start fragment list (tablet)
            ToDoDetailFragment details = new ToDoDetailFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            details.setToDoId(id);
            ft.replace(R.id.fragment_container, details);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        }else{
            //statically start fragment list (phone)
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_TODO_ID, (int) id);
            startActivity(intent);
        }

    }

    public void onClickAddTask(View view){
        EditText newTaskTitle = (EditText) findViewById(R.id.newTaskTitle);
        EditText newTaskDescription = (EditText) findViewById(R.id.newTaskDescription);
        ToDo.addToDo(newTaskTitle.getText().toString(), newTaskDescription.getText().toString());
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.to_do_list_fragmanent);

        if (fragment instanceof ToDoListFragment) {
            ToDoListFragment toDoListFragment = (ToDoListFragment) fragment;
            toDoListFragment.updateUI();
        }
    }


}