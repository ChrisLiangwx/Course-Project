package com.wl.exercise5;

import java.util.ArrayList;
import java.util.List;

public class ToDo {
    private String name;
    private String description;
    private int completeStatus;

    public static final List<ToDo> toDos = new ArrayList<>();

    static {
        toDos.add(new ToDo("My Office Worklist", "Complete the project report, attend the team meeting at 2 PM, and review the client feedback.", 2));
        toDos.add(new ToDo("My Shopping List", "Buy milk, bread, and apples from the supermarket, and don't forget to check the pharmacy for vitamins.", 1));
        toDos.add(new ToDo("My Studying List", "Read Chapter 4 of the history textbook, practice math problems, and prepare the science project outline.", 0));
    }

    private ToDo(String name, String description, int completeStatus){
        this.name = name;
        this.description = description;
        this.completeStatus = completeStatus;
    }

    public static List<ToDo> getToDos() {
        return toDos;
    }

    public static void addToDo(String name, String description) {
        toDos.add(new ToDo(name, description, 0));
    }

    public static void removeToDo(String name) {
        toDos.removeIf(todo -> todo.getName().equals(name));
    }

    public static void setCompleted(String name, int completeStatus) {
        for (ToDo todo : toDos) {
            if (todo.getName().equals(name)) {
                todo.completeStatus = completeStatus;
                break;
            }
        }
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getCompleteStatus(){
        return completeStatus;
    }

    @Override
    public String toString(){
        return this.name + " - " + completeStatus ;
    }
}
