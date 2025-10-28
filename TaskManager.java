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
    switch(choice) {
        case 1 -> addTask(scanner);
        case 2 -> ViewAllTask();
        case 3 -> ViewFirstTask();
        case 4 -> deleteTask(scanner);
        case 5 -> MarkTaskComplete(scanner);
        case 6 -> showStatistics();
        case 0 -> System.out.println("Goodbye");
        default -> System.out.println("INVALID CHOICE");
    }
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
        System.out.println("-------- We will now be adding a new task --------");
        System.out.println("Enter new task name");
        String name = scanner.nextLine();
        System.out.println("Enter the task's priority (High/Medium/Low)");
        String priority = scanner.nextLine();
        System.out.println("Enter the task's category");
        String category = scanner.nextLine();
    }
    private void ViewAllTask(){
        System.out.println(" --------The list of all Task (OLDEST FIRST)-------- ");
        taskList.displayAllTask();
    }
    private void ViewFirstTask(){
        System.out.println(" --------All task (NEWEST FIRST)-------- ");
        taskList.displayTaskBackwards();

    }
    private void deleteTask(Scanner scanner) {
        System.out.println("\n ------ Delete Task ------");
        if (taskList.isEmpty()){ // checking if the list contains anything
            System.out.println("No task to Delete");
            return;
        }
        System.out.println("Enter task name you want to delete");
        String taskName = scanner.nextLine(); // recieves the name of the task to be deleted
        
        Task deletedTask = taskList.deleteTask(taskName); //try to delete the task

        if (deletedTask != null){ // to tell us what happend
            System.out.println( "Task deleted successfully: " + deletedTask.name);

        }else{
            System.out.println("Task not found " + taskName);
        }
    }
    private void MarkTaskComplete(Scanner scanner) {

    }
    private void showStatistics() {

    } 
}
  
