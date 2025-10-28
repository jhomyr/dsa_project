package com.mycompany.dsa_final_project;

import java.util.Scanner;

public class TaskManager { //  declaration for the class TaskManager
    private LinkedList taskList; // this will hold all our task 
    Scanner scanner = new Scanner(System.in);
    public TaskManager(){ //constructor for the TaskManager 
        this.taskList = new LinkedList(); //start with an empty list of task

    }

    public void start(Scanner scanner){ //main entry for the app's system
        int choice;
do{
    displayMenu();
    System.out.println("Enter your choice!");
    choice = scanner.nextInt();
    scanner.nextLine();

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
    private void displayMenu() { //method that prints the menu options
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
        System.out.println("\n--------We will be adding new Task--------");
        System.out.println("Enter new Task name");
        String name = scanner.nextLine();
        System.out.println("Enter the task's priority (High/Medium/Low)");
        String priority = scanner.nextLine();
        System.out.println("Enter task's category");
        String category = scanner.nextLine();

        Task newTask = new Task(name, priority, category);// CREATE A NEW TASK OBJECT 
        taskList.addTask(newTask);     // ADDING IT TO OUR LIST 
        System.out.println("✓ Task added successfully");
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
        System.out.println("------ MARK TASK COMPLETE ------");

        if(taskList.isEmpty()){ // check if the list is empty
            System.out.println("No task available");
            return;
        }

        System.out.println("Enter the name of task you want to mark as complete");
        String taskName = scanner.nextLine();

        boolean success = taskList.MarkTaskComplete(taskName);


        if(success){
            System.out.println("Mark task as complete!" + taskName);
        }else{
            System.out.println("No task found" + taskName);
        }

    }
    private void showStatistics() {
        System.out.println("\n------ TASK STATISTICS ------");


        // to get total number of task
        int totalTasks = taskList.getSize();
        int completedTask = taskList.getCompleteCount();
        int pendingTask = totalTasks - completedTask;

        System.out.println("Total tasks: " + totalTasks);
        System.out.println("Completed tasks: " + completedTask);
        System.out.println("Pending tasks: " + pendingTask);

        if(totalTasks == 0){
            System.out.println("No tasks yet - add some tasks to get started");
        }else if(completedTask == totalTasks){
            System.out.println("GOOD, All task complete");
        }else{
            System.out.println("Almost done!" + pendingTask + "task pending.");
        }

    } 
}

    
