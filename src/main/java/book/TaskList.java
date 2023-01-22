package book;

import java.util.ArrayList;

import book.task.Task;

public class TaskList {
    private static ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this(new ArrayList<Task>(100));
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task get(int index) {
        return this.list.get(index);
    }
    public Task deleteTask(int index) {
        return this.list.remove(index);
    }
    public int listSize() {
        return this.list.size();
    }

    @Override
    public String toString() {
        int index = 0;
        String listString = "";
        for (Task task : this.list) {
            listString += "1.  " + task + "\n";
        }
        return listString;
    }
}
