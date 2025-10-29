package com.mycompany.dsa_final_project;

import java.util.ArrayList;
import java.util.List;

public class CategoryTree {
    private CategoryNode root;
    
    public CategoryTree(){// contructor to start an empty tree
        this.root = new CategoryNode("All Tasks");// creates root to hold all task    
    }
    public void addTaskToCategory(Task task, String categoryPath){// method for add a task to specific path
        if(categoryPath == null || categoryPath.isEmpty()){//if no path was given it will put it in root category
            root.addTask(task);// adding task to the ALL TASKS category directly
            return;// exit
        }
        String[] pathParts = categoryPath.split("/");//seperating them by"/"
        CategoryNode current = root;//make it start at the ROOT

        for(String part : pathParts){// navigate it through each pth
            CategoryNode next = current.findSubcategory(part); // look into the current categories sub category
        
            if(next == null){// if the sub category doesnt exists it creates
                current.addSubcategory(part);// create new subcategory
                next = current.findSubcategory(part);//get the new created category
            }
        current = next;
        }
        current.addTask(task);
    }
    public List<Task> getTasksFromCategory(String categoryPath) {
        // If no path given, return all tasks from root category
        if (categoryPath == null || categoryPath.isEmpty()) {
            return root.tasks;  //  Return all tasks in "All Tasks" folder
        }
        
        //  Split the path and navigate through it (same as add method)
        String[] pathParts = categoryPath.split("/");
        CategoryNode current = root;  // Start from root
        
        //  Navigate through each part of the path
        for (String part : pathParts) {
            CategoryNode next = current.findSubcategory(part);  // Look for subcategory
            if (next == null) {
                // If category doesn't exist, return empty list
                return new ArrayList<>();  //  No tasks found in non-existent category
            }
            current = next;  //  Move down to next level
        }
        
        //   Return all tasks from the found category
        return current.tasks;
    }
    
    //  create new METHOD Display the entire category tree structure with nice formatting
    public void displayCategoryTree() {
        System.out.println("------ CATEGORY TREE ------");
        displayCategoryNode(root, 0);  //  Start displaying from root with indentation level 0
    }
    
    //  method to Display a category node and all its children (RECURSIVE)
    // This method calls itself to display subcategories - that's why it's recursive!
    private void displayCategoryNode(CategoryNode node, int depth) {
        //   Create indentation based on depth (deeper categories = more indented)
        // Example: depth 0 = "", depth 1 = "  ", depth 2 = "    "
        String indent = "  ".repeat(depth);
        
        //   Print this category with folder icon and proper indentation
        System.out.println(indent + ">" + node.toString());
        
        // Display all tasks in this category with task icon
        for (Task task : node.tasks) {
            System.out.println(indent + ">" + task.name);
        }
        
        //  Recursively display all subcategories 
        for (CategoryNode subcategory : node.subcategories) {
            displayCategoryNode(subcategory, depth + 1);  //  Call itself with increased depth!
        }
    }
}

