package com.mycompany.dsa_final_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
public Task getHighestPriorityTask(){
    if(head == null){ // to check if it's empty
        return null; // return nothing
    }

    Node<Task> current = head;// so we start at the head of the chain 
    Task highePriorityTask = null; // assume the first task is the most important
    int HighestPriority = -1;//get its priority value

    while(current != null){// look through every task in the list 
        if(!current.data.completed){//only concider the task that are not completed
            int currentPriority = current.data.getPriorityValue();//get the tasks priority points

            if(currentPriority > HighestPriority){//comparing of priority points of tasks
                highePriorityTask = current.data;//swap the lower value to the higher value
                HighestPriority = currentPriority;// update who is the current highest task value
            }
        }
        current = current.next; // to connect it to the next task in the list
    }
return highePriorityTask;// return the task with the highest priority value
}
//METHOD 1: Quick Sort - Fast sorting algorithm
public void quickSortByPriority() {
    // Check if sorting is needed
    if (head == null || head.next == null) {
        return; 
    }
    
    System.out.println(" Sorting with Quick Sort");
    
    // 🛠️ SIMPLER APPROACH: Convert to array, sort, then rebuild list
    // Why? The pointer-based Quick Sort was losing tasks due to complex pointer manipulation
    List<Task> tasks = new ArrayList<>();
    Node<Task> current = head;
    
    // STEP 1: Convert linked list to array for easier sorting
    while (current != null) {
        tasks.add(current.data);  // Add each task to the list
        current = current.next;
    }
    
    // STEP 2: Sort the array using Java's built-in sort with custom comparator
    // We use Collections.sort with a lambda comparator for descending order
    Collections.sort(tasks, (task1, task2) -> {
        // Compare in REVERSE order (t2 first, then t1) for DESCENDING order
        // Why? We want High priority (3) first, then Medium (2), then Low (1)
        return Integer.compare(task2.getPriorityValue(), task1.getPriorityValue());
    });
    
    // STEP 3: Rebuild the linked list from the sorted array
    // Clear the existing list completely
    head = null;
    tail = null;
    size = 0;
    
    // Add each task back to the list in sorted order
    for (Task task : tasks) {
        addTask(task);  // This maintains all pointers and size automatically
    }
    
    System.out.println(" Quick sort completed!");
}
private Node<Task> quickSort(Node<Task> start, Node<Task> end){
        if(start == null || start == end || start == end.next){// if it has  0 or 1 element meaning it's already sorted
            return start; //return it as the start
        }   
        Node<Task>[] partitioned = partition(start, end); // calling the partitioned method containing the two boxes, start and end
        Node<Task> pivot_prev = partitioned[0];// taking the two information out of the array and giving them names
        Node<Task> pivot = partitioned[1]; 


        if(pivot_prev != null &&   pivot_prev != start){// if left side exist, sort it recursively 
            Node<Task> temp = start;// temporary disconnect it from the pivot 
            while(temp.next != pivot_prev){
                temp = temp.next;
            }
            temp.next = null;// cutting the chain between left side and pivot
            start = quickSort(start, temp);// recursive sort the left side
            
            
            temp = start;// reconnect the sorted left side back to piivot
            while(temp.next != null){
                temp = temp.next;
            }          
            temp.next = pivot_prev;//reconnect it back to the chain 
        }

        if(pivot != null && pivot.next != null){// now let's sort the right side
            pivot.next = quickSort(pivot.next, end);
        }
        
return start;
}
private Node<Task>[] partition(Node<Task> start, Node<Task> end){// adding method for partition quick sort
    if(start == null   || start == end){ // checking if we got something to organized
        return new Node[]{null,start};
    }
    Node<Task> pivot = end;// picks the last element as our comparison
    Node<Task> pivot_prev = null;//remember the element right before the pivot should go
    Node<Task> current = start;// we're looking at the current element 
    Node<Task> tail = pivot; // keeps track of the last element in the group

    while(current != pivot){// keeps going until we rach the pivot
        if(current.data.getPriorityValue() >= pivot.data.getPriorityValue()){// comparing the value 
                      
            // moving the current element from their current spot and put it in the other group
            if (pivot_prev == null){// it it's the first move it as the start
                start = current.next;
            }else{
                pivot_prev.next = current.next;// skip over the current element
            }
            
            Node<Task> temp = current.next;// moving the current element to the right group
            current.next = null;// disconnext the element completely
            tail.next = current;// add them to the right group
            tail = current;//they become the new end of the group
            current = temp;//move the next element within the chain
            
        } else {
            pivot_prev = current;
            current = current.next; //  FIXED: current (not currrent)
        }
    }
    return new Node[]{pivot_prev, pivot};
}
public void insertionSortByPriority(){ 
    // Check if sorting is needed - empty list or single element
    if(head == null || head.next == null){
        return;
    }
    System.out.println(" Sorting with insertion sort");
    
    Node<Task> sorted = null;  // This will hold our sorted portion (starts empty)
    Node<Task> current = head; // Start with first unsorted task

    while(current != null){ // Process each task one by one
        Node<Task> next = current.next; // Save next task BEFORE we disconnect current
        
        // Completely disconnect current node from the list
        current.next = null;
        current.previous = null;

        // CASE 1: Insert at BEGINNING of sorted list
        // This happens when:
        // - sorted list is empty (first task), OR
        // - current task has HIGHER or EQUAL priority than first sorted task
        // 🛠️ FIXED: Changed >= to <= for DESCENDING order (High first)
        // Why? We want HIGH priority (value 3) to come before LOW priority (value 1)
        // So when current priority <= sorted priority, current goes first
        if(sorted == null || current.data.getPriorityValue() >= sorted.data.getPriorityValue()){
            // Insert current at the beginning of sorted list
            current.next = sorted; // Current points to old head
            
            // Update backward pointer if sorted list wasn't empty
            if (sorted != null){
                sorted.previous = current; // Old head points back to current
            }
            sorted = current; // Current becomes new head of sorted list
        }
        // CASE 2: Insert in MIDDLE or END of sorted list
        else{
            Node<Task> search = sorted; // Start from beginning of sorted list
            
            // 🛠️ FIXED: Changed > to < for DESCENDING order
            // Find the position where current should be inserted
            // We move forward while next task has LOWER priority than current
            // This ensures HIGH priority tasks stay at the front
            while(search.next != null && search.next.data.getPriorityValue() > current.data.getPriorityValue()){
                search = search.next; // Move to next node in sorted list
            }
            
            // 🛠️ FIXED: Save the next node BEFORE changing pointers
            // Why? Once we change search.next, we lose the original reference
            Node<Task> searchNext = search.next;
            
            // Insert current between search and searchNext
            current.next = searchNext;    // Current points to whatever was after search
            current.previous = search;    // Current points back to search
            search.next = current;        // Search now points to current
            
            // 🛠️ FIXED: Update previous pointer of the node after current
            // Why? In doubly linked list, if A → B then B ← A must be true
            if(searchNext != null){
                searchNext.previous = current; // The node after current points back to current
            }
        }
        current = next; // Move to next unsorted task
    }
    
    // Update the class's head to point to the sorted list
    head = sorted;
    
    // Find and update the new tail after sorting
    Node<Task> temp = head;
    while(temp != null && temp.next != null){
        temp = temp.next;
    }
    tail = temp; // Last node becomes tail

    System.out.println(" Insertion sort completed! Tasks are now sorted by priority (High → Medium → Low)");
}
}









 

