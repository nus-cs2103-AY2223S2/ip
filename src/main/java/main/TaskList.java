package main;

import java.util.ArrayList;

import task.Task;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void mark(int inputIndex) {
        this.list.get(inputIndex - 1).mark();
    }

    public void unmark(int inputIndex) {
        this.list.get(inputIndex - 1).unmark();
    }

    public void add(Task newTask) {
        this.list.add(newTask);
    }

    public void delete(int inputIndex) {
        this.list.remove(inputIndex - 1);
    }

    public Task get(int inputIndex) {
        return this.list.get(inputIndex - 1);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public int size() {
        return list.size();
    }
}
