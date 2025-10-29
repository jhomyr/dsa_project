package com.mycompany.dsa_final_project;

import java.util.ArrayList;
import java.util.List;

public class CategoryNode {

    public String name; //name for the category folder
    public List<CategoryNode> subcategories; // sub foler for inside the fold
    public List<Task> tasks; // list of task 

    public CategoryNode(String name){// constructor
        this.name = name; // where we set the folders name
        this.subcategories = new ArrayList<>();// empty list for the sub folder
        this.tasks = new ArrayList<>();//empty list of tasks
    }
    
    public void addSubcategory(String subcategoryName){//method for adding new sub folder  
        CategoryNode newSubCategory = new CategoryNode(subcategoryName); // adding new category for our subfolder
        subcategories.add(newSubCategory);// adding the subfolder to our list of categories
    }
    public void addTask(Task task){// method for for apublic void addTask(Task task){// method for for adding task to the category folder
        tasks.add(task);// adding it to our list
    }
    public CategoryNode findSubcategory(String name){//method for finding sub category by name
        for(CategoryNode subcategory : subcategories){// looking through all the sub folder
            if(subcategory.name.equals(name)){// checking if it matches the name 
                return subcategory;// found it and returns in the sub folder
            }
        }
        return null; // no sub folder matches the name
    }
@Override // format when printing 
public String toString(){
    return name + " (" + tasks.size() + " tasks, " + subcategories.size() + " subcategories)"; 
}
    
}
