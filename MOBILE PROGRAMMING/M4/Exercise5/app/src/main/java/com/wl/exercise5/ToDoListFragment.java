package com.wl.exercise5;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;




public class ToDoListFragment extends ListFragment {
    static interface Listener{
        void itemClicked(long id);
    };

    private Listener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        String[] names = new String[ToDo.getToDos().size()];
        for(int i = 0; i < names.length; i++){
            ToDo toDo = ToDo.toDos.get(i);
            names[i] = toDo.getName();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(inflater.getContext(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.listener = (Listener) context;
    }

    @Override
    public void onListItemClick (ListView listView, View itemView, int position, long id){
        if(listener != null){
            listener.itemClicked(id);
        }
    }

    public void updateUI() {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < ToDo.getToDos().size(); i++) {
            ToDo toDo = ToDo.getToDos().get(i);
            names.add(toDo.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, names);
        setListAdapter(adapter);
    }

}