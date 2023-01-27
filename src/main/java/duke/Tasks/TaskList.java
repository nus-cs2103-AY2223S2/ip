package duke.Tasks;

import duke.Tasks.Task;
import  java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> list;


    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task t) {
        list.add(t);
    }

    public Task removeTask(int i) {
        return list.remove(i - 1);
    }

    public void markDone(int i) {
        list.get(i - 1).markDone();
    }
    public void markNotDone(int i) {
        list.get(i - 1).markNotDone();
    }

    public Task get(int i) {
        return list.get(i - 1);
    }

    public int size() {
        return list.size();
    }



}
