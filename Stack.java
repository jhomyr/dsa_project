package com.mycompany.dsa_final_project;

public class Stack {
    public static class ActionRecord{ //where we will store the records 
        public String action; // to add or delete
        public Task task; // where to put the task involved
        public Boolean oldStatus; // For mark complete the old status

        public ActionRecord(String action, Task task){ // constructor 
            this.action = action;
            this.task = task;
            this.oldStatus = null;
        }
        public ActionRecord(String action, Task task, boolean oldStatus){ // constructor 
            this.action = action;
            this.task = task;
            this.oldStatus = oldStatus;
        }

    }    
    private Node<ActionRecord> top; // top of the stack


    public Stack(){
        this.top = null; //to start empty within the stack
    }
    // to push new action to the stack 
    public void push (String action, Task task){
        ActionRecord record = new ActionRecord(action, task); // where we record the action and task 
        Node<ActionRecord> newNode = new Node<>(record); // to store the records to get back later again
        newNode.next = top;//new node points to the old top
        top = newNode; // new node becomes the top
    }
    public void push(String action, Task task, boolean oldStatus){
        ActionRecord record = new ActionRecord(action, task, oldStatus);
        Node<ActionRecord> newNode = new Node<>(record);
        newNode.next = top;
        top = newNode;
    }
    public ActionRecord pop(){
        if(top == null)return null; // check if stack is empty
        ActionRecord record = top.data; // opening the folder we created a while ago
        top = top.next; // pointing to the next folder
        return record;   
    }
    public boolean isEmpty(){
        return top == null;
    }
}
