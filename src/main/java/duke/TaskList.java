package duke;

import java.util.ArrayList;

public class TaskList {
    static ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public void delete(int i) {
        list.remove(i);
    }

    public Task get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public void markAsDone(int i) {
        list.get(i).markAsDone();
    }

    public void markAsUnDone(int i) {
        list.get(i).markAsUnDone();
    }

    public static ArrayList<Task> getList() {
        return list;
    }


 



}
