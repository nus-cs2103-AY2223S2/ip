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

    public TaskList find(String input) {
        ArrayList<Task> newTaskList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).toString().contains(input)) {
                newTaskList.add(list.get(i));
            }
        }
        return new TaskList(newTaskList);
    }
}
