package com.mycompany.dsa_final_project;
import java.util.Scanner;

public class DSA_Final_Porject{
 public static void main(String[]args){
       Scanner scanner = new Scanner(System.in);
       TaskManager taskManager = new TaskManager();
       System.out.print("Welcome to the Task Manager");

    taskManager.start(scanner);
    scanner.close();
}
}
