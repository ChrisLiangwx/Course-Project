package com.wl.workout;

public class Workout {
    private String name;
    private String description;

    public static final Workout[] workouts = {
        new Workout("The Limb Loosener", "5 Handstand push-ups\n10 1-legged squats\n15 Pull-uop"),
        new Workout("Core Agony", "5 Handstand push-ups\n10 1-legged squats\n15 Pull-uop"),
        new Workout("The Wimp Special", "5 Handstand push-ups\n10 1-legged squats\n15 Pull-uop"),
        new Workout("Strength and Length", "5 Handstand push-ups\n10 1-legged squats\n15 Pull-uop")
    };

    private Workout(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public String getName(){
        return name;
    }

    public String toString(){
        return this.name;
    }

}
