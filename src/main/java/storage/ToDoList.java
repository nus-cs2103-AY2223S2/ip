package storage;

import java.util.ArrayList;

/**
 * Represents the to do list
 * @author clevon-w
 */
public class ToDoList {
    /**
     * Represents the to do list
     */
    private ArrayList<String> toDoList;

    /**
     * Creates an empty ArrayList of Strings as an empty to do list
     */
    public ToDoList() {
        this.toDoList = new ArrayList<String>();
    }

    /**
     * Adds a task into the to do list
     * @param newToDo The new task to be added
     */
    public void createToDo(String newToDo) {
        this.toDoList.add(newToDo);
    }

    /**
     * Custom String representation of the to do list
     * @return Custom string representation
     */
    @Override
    public String toString() {
        String res = "Here are the tasks in your list:\n";
        if (toDoList.size() == 0) {
            return "There are currently no tasks in your to do list,\n\t create one now!";
        }
        for (int i = 0; i < toDoList.size(); i++) {
            res += "\t " + (i+1) + ". " + toDoList.get(i) + "\n";
        }
        return res.trim();
    }
}
