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

    public String matchingTasks(String keyword) {
        int index = 1;
        String listString = "";
        for (Task task : this.list) {
            if (task.containsKeyword(keyword)) {
                listString += index++ + ".  " + task + "\n";
            } else {
                index++;
            }
        }
        return listString;
    }

    @Override
    public String toString() {
        int index = 1;
        String listString = "";
        for (Task task : this.list) {
            listString += index++ + ".  " + task + "\n";
        }
        return listString;
    }
}
