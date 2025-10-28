package com.mycompany.dsa_final_project; //package to put in all the classes

import java.util.Scanner; //import so we can use user input

public class DSA_FINAL_PROJECT {        //this is the entry class point that contains the main class
    public static void main(String[]args){  //where it starts running

        Scanner scanner = new Scanner(System.in); // creating "scanner" to take user input
        
        TaskManager taskManager = new TaskManager(); // calling the TaskManager to taskManager 
    
        System.out.println("Welcome to the Task Manager System"); //print out 
    
    taskManager.start(scanner); //this just callthat taskManager then getting the user input as choice
    scanner.close();// to exit the program
    }
}
