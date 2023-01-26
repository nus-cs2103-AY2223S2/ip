package duke;

import duke.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void add(Task t) {
        this.list.add(t);
    }

    public void remove(int index) {
        this.list.remove(index);
    }

    public void mark(int index) {
        this.list.get(index).setMark(true);
    }

    public int size() {
        return this.list.size();
    }
    public void print() {
        for (int i = 0; i < list.size(); i++) {
            int j = i + 1;
            System.out.println(j + "." + list.get(i));
        }
    }



}
