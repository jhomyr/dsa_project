package com.mycompany.dsa_final_project;

import java.util.Scanner;


public class LinkedList {
    private Node<Task> head;    
    private Node<Task> tail;
    private int size;

public LinkedList(){
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
    if(head == null){
        System.out.println("No task found. ");
        return;
}
    Node<Task> current = head;
    int index = 1;

    while(current != null){
        System.out.println(index + ". " + current.data);
        current = current.next;
        index++
    }
}
public void displayTaskBackwards(){
    if(tail == null){
    System.out.println("No task found. ");
    return;
    }
    System.out.println(" ------ TASKS (NEWEST FIRST)------ ");
        Node<Task> current = tail;
            int index = size;
    while(current != null){
        System.out.println(index + ". " + current.data );
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
}
