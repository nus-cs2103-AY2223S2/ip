package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> toDoList;

    public TaskList() {
        this.toDoList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> toDoList) {
        this.toDoList = toDoList;
    }

    public int size() {
        return this.toDoList.size();
    }

    public Task get(int i) {
        return this.toDoList.get(i);
    }

    public void add(Task task) {
        this.toDoList.add(task);
    }

    public void remove(int i) {
        this.toDoList.remove(i);
    }

    public ArrayList<Task> search(String searchString) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        
        for (int i = 0; i < this.toDoList.size(); i++) {
            if(toDoList.get(i).contains(searchString)){
                foundTasks.add(toDoList.get(i));
            }
        }

        return foundTasks;
    }
}
