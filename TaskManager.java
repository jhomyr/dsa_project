package com.mycompany.dsa_final_project;

import java.util.Scanner;

public class TaksManager(){
    private LinkedList taskList;
    
    
    public TaskManager(){
        this.taskList = new LInkedList();
    }


    public void start(Scanner scanner){
    int choice;    
do{
    displayMenu();
    System.out.println("Enter your choice");
    choice = scanner.nextInt();
    scanner.nextInt();
    



    
} while (choice != 0);
  
}
     private void displayMenu() {
        System.out.println("\n--- MAIN MENU ---");  // Print header
        System.out.println("1. Add New Task");      // Option 1
        System.out.println("2. View All Tasks (Oldest First)"); // Option 2
        System.out.println("3. View Tasks (Newest First)");     // Option 3
        System.out.println("4. Delete Task");       // Option 4
        System.out.println("5. Mark Task Complete"); // Option 5
        System.out.println("6. Show Statistics");   // Option 6
        System.out.println("0. Exit");

    }
    private void addTask(Scanner scanner) {

    }
    private void ViewAllTask(){

    }
    private void ViewFirstTask(){

    }
    private void deleteTask(Scanner scanner) {

    }
    private void MarkTaskComplete(Scanner scanner) {

    }
    private void showStatistics() {

    } 
}
  
