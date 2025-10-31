package com.mycompany.dsa_final_project;

public class Task {
    public String name; //initiation for name
    public String priority;
    public String category;
    public Boolean completed;

    //constructor for the new task when we want to add in the main  menu
    public Task(String name, String priority, String category){
        this.name = name;
        this.priority = priority;
        this.category = category;
        this.completed = false;
    }
    //constructor used when restoring a task
    public Task(String name, String priority, String category, Boolean completed){
        this.name = name;
        this.priority = priority;
        this.category = category;
        this.completed = completed;
    }


    // where we overide the already something into something we want and to a string 
    @Override
    public String toString() {
        String status = completed ? "Done" : "Pending";
            return "[" + status + "] " + name
             + " | Priority: " + priority
             + " | Category: " + category;
    }
    public int getPriorityValue(){// method in converting priority value to a number comparison
        String lowerPriority = priority.toLowerCase();// convert priority to lowercase so high, HIGH all works the same

        switch(lowerPriority){
            case "high":
                return 3;// most important
            case "medium":
                return 2;//less important
            case "low":
                return 1;// least important
            default:
                return 0;//if input is invalid 
        }
    }
}

