package com.mycompany.dsa_final_project;


public class Node<T> { //generic  node T to let's as reuse it for task, and can use any datatype
    public T data; // this is where we store, think of it as a box 
    public Node<T> next; // pointer or reference to the next node of the chain 
    public Node<T> previous; // pointer or reference to the previous node in the chain


public Node(T data){ // contructor with the parameter of new thing, like it enables you to create a box with new thing 
    this.data = data; // where we put the thing
    this.next = null; // where we connect the box to the next box
    this.previous = null; // where we connect the box to the past box

}
}
