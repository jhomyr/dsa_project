package com.mycompany.dsa_final_project;

import java.util.Scanner;

public class Task{
  public String name; //initiation for the string so we can just make a constructor for specific paremeters
  public String priority;
  public String category;
  public Boolean completed;
}

public class Task(String name, String priority, String category){ // constructor for this specific paremeter where we will put the main menu
  this.name = name;
  this.priority = priority;
  this.category = category;
}
public class Task(String name, String priority, String category, Boolean completed){ // constructor with different paremeter 
  this.name = name;
  this.priority = priority;
  this.category = category;
  this.completed = completed;
}

@override
  public String toString(){
    String status = completed ? "Done" : "Pending";
      return "[" + status + "] " + name
             + " | Priority: " + priority
             + " | Category: " + category;
    }
}
