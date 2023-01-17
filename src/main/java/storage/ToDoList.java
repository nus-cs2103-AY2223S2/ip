package storage;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<String> toDoList;

    public ToDoList() {
        this.toDoList = new ArrayList<String>();
    }

    public void createToDo(String newToDo) {
        this.toDoList.add(newToDo);
    }

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
