package com.wl.exercise5;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;



public class ToDoDetailFragment extends Fragment {
    private long toDoId;
    private View rootView;



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putLong("toDoId", toDoId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            toDoId = savedInstanceState.getLong("toDoId");
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        View view = getView();
        if(view != null){
            ToDo toDo = ToDo.toDos.get((int)toDoId);
            //title
            TextView title = (TextView) view.findViewById(R.id.taskTitle);
            title.setText(toDo.getName());
            //description
            TextView description = (TextView) view.findViewById(R.id.taskDescription);
            description.setText(toDo.getDescription());
            //spinner status
            Spinner taskStatusSpinner = (Spinner) view.findViewById(R.id.taskStatusSpinner);
            //status
            TextView status = (TextView) view.findViewById(R.id.taskStatus);
            switch (toDo.getCompleteStatus()) {
                case 0:
                    status.setText("To do");
                    break;
                case 1:
                    status.setText("Doing");
                    break;
                case 2:
                    status.setText("Done");
                    break;
            }
            taskStatusSpinner.setSelection(toDo.getCompleteStatus());


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_to_do_detail, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Spinner taskStatusSpinner = view.findViewById(R.id.taskStatusSpinner);
        taskStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 当选项被选择时的处理
                onClickChangeStatus(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 当没有选项被选择时的处理
            }
        });

        Button deleteButton = view.findViewById(R.id.deleteTask);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDeleteTask(view);
            }
        });
    }

    public void onClickChangeStatus(int position) {
        //find the name of the task
        ToDo toDo = ToDo.toDos.get((int)toDoId);
        TextView title = (TextView) rootView.findViewById(R.id.taskTitle);
        String taskName = title.getText().toString();

        // 更新任务的完成状态
        ToDo.setCompleted(taskName, position);

        // 更新状态文本
        TextView taskStatus = (TextView) rootView.findViewById(R.id.taskStatus);
        switch (position) {
            case 0:
                taskStatus.setText("To do");
                break;
            case 1:
                taskStatus.setText("Doing");
                break;
            case 2:
                taskStatus.setText("Done");
                break;
        }
    }

    public void onClickDeleteTask(View view) {
        //find the name of the task
        ToDo toDo = ToDo.toDos.get((int)toDoId);
        TextView title = (TextView) rootView.findViewById(R.id.taskTitle);
        String taskName = title.getText().toString();
        ToDo.removeToDo(taskName);

        //renew MainActivity
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        //end DetailActivity
        if (getActivity() != null) {
            getActivity().finish();
        }

    }



    public void setToDoId(long id){
        this.toDoId = id;
    }


}