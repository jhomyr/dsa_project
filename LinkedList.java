package com.mycompany.dsa_final_project;


public class LinkedList {
    private Node<Task> head; // The very first task in the chain 
    private Node<Task> tail; // The very last task in the chain 
    private int size; //


public LinkedList(){ //constructor create a new linklist, which is empty
    this.head = null;
    this.tail = null;
    this.size = 0;
}

public void addTask(Task task){
    Node<Task> newNode = new Node<>(task); // to wrap in a box (node)
    
    if(head == null){ //if the list is empty
        head = newNode; //this will make the new task the first 
        tail = newNode; //and the last 
    } else { // but if there's a task already
        tail.next = newNode; // currect last task points to the new task 
        newNode.previous = tail;// new task points to the old last task 
        tail = newNode;// new task will become the new last task 
    }
    size++; // every time if there's something add the size will also expand
}
public void displayAllTask(){
    if(head == null){ // to check if the current chain is empty
        System.out.println(" No task found. "); 
        return;
    }
    Node<Task> current = head; //initalized current and make it point as the head
    int index = 1; //create new variable for the lines

    while(current != null){
        System.out.println(index + ". " + current.data);
        current = current.next;
        index++;
    }
}
public void displayTaskBackwards(){
    if(tail == null){
        System.out.println(" No tasks found ");
        return;
    }
    System.out.println(" ------ TASKS (NEWEST FIRST)------ ");
    Node<Task> current = tail;
    int index = size;
    while (current != null){
        System.out.println(index + ". " + current.data);
        current = current.previous;
        index--;
    }
}
public boolean isEmpty(){// tells us that if their's a file
    return head == null;
}
public Task removeNode(Node<Task> nodeToRemove){
    Task removedTask = nodeToRemove.data; //

    if (nodeToRemove.previous == null){ //If we're removing the FIRST task, update the head. If not, connect the previous task to the next task.
        head = nodeToRemove.next;
    }else{
        nodeToRemove.previous.next = nodeToRemove.next;
    }
    if (nodeToRemove.next == null) {// If we're removing the LAST task, update the tail. If not, connect the next task to the previous task.
        tail = nodeToRemove.previous;
    }else{
        nodeToRemove.next.previous = nodeToRemove.previous;
    }
    size--;
    return removedTask;
}

public Task deleteTask(String taskName){
    if(head == null){ // to check if it's empty
        return null;
    }
    //Start searching from the first task
    //Begin looking from the very first file in the cabinet
    Node<Task> current = head;

    while(current != null){ // check if it's not null
        if(current.data.name.equalsIgnoreCase(taskName)){ // checks one by one in the data it it's equal to the task name inputed by the user we make it case insensitive too
            return  removeNode(current);// if found remove it then return the deleted task
        }
        current = current.next;// to move it to the next file if not right file found
    }
    return null;
}
public boolean MarkTaskComplete(String taskName){
    if(head == null){ // cheack it chain is empty
        return false;//no task to be mark complete
    }
    Node<Task> current = head; // to start seaching the head which is now called current

    while(current != null){ //double checking the head which is current not empty
        if(current.data.name.equalsIgnoreCase(taskName)){ // finds the user input which is taskName wthin the file we also make it case insensitive
            current.data.completed = true; // if found mark it compelete
            return true;//return value true
        }
        current = current.next; //move to the next task
    }
    return false;// task is not found 

}
public int getCompleteCount(){
    int count = 0; // to start the count or counter

    Node<Task> current = head; //to start the task from the head

    while(current != null){ // to look if it's empty
        if(current.data.completed){ // to checking if it's mark completed
            count++;//addition for our counter
        }
        current = current.next;
    }
    return count;
}
public int getSize(){
    return size;
}
public Task findTaskByName(String taskName){
    if(head == null){
    return null;
}
Node<Task> current = head;


while(current != null){
    if(current.data.name.equalsIgnoreCase(taskName)){
        return current.data;
    }
    current = current.next;
}
return null;
}
}





 

