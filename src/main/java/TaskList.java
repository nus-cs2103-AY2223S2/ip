import java.io.*;
import java.util.*;

public class TaskList {
    private List<Task> toDoList;
    public TaskList() {
        this.toDoList = new ArrayList<>();
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
    public int size() {
        return this.toDoList.size();
    }

    public String toString() {
        return "" + this.toDoList;
    }

}