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
