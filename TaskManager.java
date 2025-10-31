package com.mycompany.dsa_final_project;

import java.util.List;
import java.util.Scanner;

public class TaskManager { //  declaration for the class TaskManager
    private LinkedList taskList; // this will hold all our task 
    private CategoryTree categoryTree;//category organization system
    private Stack undoStack;// to hold undo action
    Scanner scanner = new Scanner(System.in);
    public TaskManager(){ //constructor for the TaskManager 
        this.taskList = new LinkedList(); //start with an empty list of task
        this.undoStack = new Stack();//to start an empty undo stack
        this.categoryTree = new CategoryTree();//initialized category tree

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
        case 7 -> undoLastAction();
        case 8 -> viewTasksByCategory();  
        case 9 -> displayCategoryTree();
        case 10 -> showNextTask();          
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
        System.out.println("7. Undo Last Action");
        System.out.println("8. View Tasks by Category");  
        System.out.println("9. Display Category Tree");
        System.out.println("10. Show Next Task (Highest Priority)"); 
        System.out.println("0. Exit");

    }
    private void addTask(Scanner scanner) {
        System.out.println("\n--------We will be adding new Task--------");
        System.out.println("Enter new Task name");
        String name = scanner.nextLine();
        System.out.println("Enter the task's priority (High/Medium/Low)");
        String priority = scanner.nextLine();
        System.out.println("Enter task's category path (e.g., School/CS203 or just Work):");
        System.out.println("Leave empty for 'All Tasks'");
        String categoryPath = scanner.nextLine();
        
        Task newTask = new Task(name, priority, categoryPath);// CREATE A NEW TASK OBJECT 

        taskList.addTask(newTask);     // ADDING IT TO OUR LIST 
        
        categoryTree.addTaskToCategory(newTask, categoryPath);
        
        undoStack.push("ADD", newTask);//We're telling our memory bank: "Remember I just ADDED this task, in case I want to undo it later"
        
         System.out.println("✓ Task added successfully to category: " + 
                      (categoryPath.isEmpty() ? "All Tasks" : categoryPath));
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
            undoStack.push("DELETE", deletedTask);//We're telling our memory bank: "Remember I just ADDED this task, in case I want to undo it later"
            System.out.println( "Task deleted successfully: " + deletedTask.name);

        }else{
            System.out.println("Task not found " + taskName);
        }

    }
    private void MarkTaskComplete(Scanner scanner) {
    // Show the user what section they're in
    System.out.println("------ MARK TASK COMPLETE ------");

    // First, check if there are any tasks at all
    // Why? Can't mark tasks complete if there are no tasks!
    if(taskList.isEmpty()){ 
        System.out.println("No task available");
        return;  // Exit the method early - nothing to do
    }
    System.out.println("Available tasks");
    taskList.displayAllTask();

    // Ask the user which task they want to mark
    System.out.println("Enter the name of task you want to mark as complete");
    String taskName = scanner.nextLine();  // Read the task name from user input

    // Try to find the task in our list
    // Why? We need to get the task object to check its current status
    Task taskToMark = taskList.findTaskByName(taskName);
    
    // Check if we actually found the task
    if(taskToMark != null){
        // Save the OLD completion status BEFORE we change it
        // For undo feature - we need to remember what it was before
        boolean oldStatus = taskToMark.completed;

        // Actually mark the task as complete/incomplete
        // This flips the completed status (true→false or false→true)
        boolean success = taskList.MarkTaskComplete(taskName);

        // Check if the marking operation was successful
        if(success){
            // Record this action for UNDO feature
            //So user can undo if they made a mistake
            // We save: action type, which task, and what the OLD status was
            undoStack.push("MARK_COMPLETE", taskToMark, oldStatus);
            
            // Tell the user what happened
            if(oldStatus){
                // If oldStatus was TRUE, task was completed → now it's INCOMPLETE
                System.out.println("Task marked as INCOMPLETE: " + taskName);
            } else {
                // If oldStatus was FALSE, task was pending → now it's COMPLETE  
                System.out.println("Task marked as COMPLETE: " + taskName);
            }
        }
    } else {
        // If taskToMark is null, the task wasn't found
        System.out.println("Task not found: " + taskName);
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
    private void undoLastAction(){
        System.out.println("\n------ UNDO LAST ACTION ------ ");

        if (undoStack.isEmpty()){ // to check if there's anything to undo 
            System.out.println("No action to undo");
            return;
        }
        Stack.ActionRecord lastAction = undoStack.pop();//to get the last action from the stack

        if(lastAction.action.equals("ADD")){// if the last action was add we need to delete the task
            taskList.deleteTask(lastAction.task.name);
            System.out.println("UNDO ADD operation for: " + lastAction.task.name);
        }else if (lastAction.action.equals("DELETE")){// if the action is delete we need to add back
            Task restoredTask = new Task(
                lastAction.task.name,
                lastAction.task.priority,
                lastAction.task.category,
                lastAction.task.completed
            );
            taskList.addTask(restoredTask);
            System.out.println("UNDO DELETE operation for: " + lastAction.task.name);
        
        }else if(lastAction.action.equals("MARK_COMPLETE")){
            //  When user marked a task complete/incomplete, we saved the OLD status
            // Now we need to find that task and restore its previous completion status
            // Find the task in our list
            // Why? We need to get the actual task object to modify its completion status
            Task taskToRestore = taskList.findTaskByName(lastAction.task.name);
            
            if(taskToRestore != null){
                taskToRestore.completed = lastAction.oldStatus;
            
                if(lastAction.oldStatus){
                    System.out.println("UNDO MARK_COMPLETE - task is now COMPLETED " + lastAction.task.name); 
            }else{
                    System.out.println("UNDO MARK_COMPLETE - task is now PENDING " + lastAction.task.name);
                }    
            }
        }
    }
        private void viewTasksByCategory() {
            System.out.println("\n--- VIEW TASKS BY CATEGORY ---");
            System.out.println("Enter category path (e.g., School/CS203 or leave empty for All Tasks):");
            String categoryPath = scanner.nextLine();
    
            //  Get tasks from the category tree
            List<Task> categoryTasks = categoryTree.getTasksFromCategory(categoryPath);
    
            if (categoryTasks.isEmpty()) {
                    System.out.println("No tasks found in category: " + 
                          (categoryPath.isEmpty() ? "All Tasks" : categoryPath));
                } else {
                    System.out.println("--- Tasks in " + 
                          (categoryPath.isEmpty() ? "All Tasks" : categoryPath) + " ---");
                    int index = 1;
                    for (Task task : categoryTasks) {
                    System.out.println(index + ". " + task);
                    index++;
        }
    }
}
        private void displayCategoryTree() {
        System.out.println("\n--- CATEGORY TREE STRUCTURE ---");
        categoryTree.displayCategoryTree();  // This will show the entire folder structurenull
        }
        public void showNextTask(){
            if(taskList.isEmpty()){ // to check if there's a tasks in the chain
                System.out.println("No tasks available. Add some tasks to get started!!!");
                return; //exits 
            }

            Task nextTask = taskList.getHighestPriorityTask();// ask our linkList to find the highest priorirty task
            if(nextTask == null){// check if we found a task
                System.out.println("All tasks are completed");
            }else{//we found the next task we need to do base on priority
                System.out.println(" \n------ YOUR NEXT TASKS ----5-- ");
                System.out.println(nextTask.name);
                System.out.println("Priority: " + nextTask.priority);
                System.out.println("Category: " + (nextTask.category.isEmpty() ? "No Category" : nextTask.category));
                System.out.println("Status: " + (nextTask.completed ? "Completed" : "Pending"));
            }
            int priorityValue = nextTask.getPriorityValue(); // 
            if(priorityValue == 3){
                System.out.println("This is HIGH priority - consider doing this first!");
            } else if (priorityValue == 2) {
            System.out.println("This is MEDIUM priority - to do soon!");
            } else if (priorityValue == 1) {
            System.out.println("This is LOW priority - can wait if needed!");
            }
        }
    }


    


    
