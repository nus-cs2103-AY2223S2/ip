package treebot.tasks;

import treebot.interfaces.ITaskList;

import java.util.ArrayList;

public class TaskListStub implements ITaskList {


    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskListStub() {
        Todo task1 = new Todo("task 1");
        Todo task2 = new Todo("task 2");
        Todo task3 = new Todo("task 3");
        this.taskList.add(task1);
        this.taskList.add(task2);
        this.taskList.add(task3);
    }


    @Override
    public ArrayList<Task> getArrayListCopy() {
        return null;
    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public void addTask(Task task, int index) throws IndexOutOfBoundsException {

    }

    @Override
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public Task deleteTask(Task task) {
        return null;
    }

    @Override
    public Task markTask(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index).markAsDone();
        return this.taskList.get(index);
    }

    @Override
    public Task unmarkTask(int index) throws IndexOutOfBoundsException {
        this.taskList.get(index).markAsUndone();
        return this.taskList.get(index);
    }

    @Override
    public ITaskList find(String keyword) {
        return null;
    }

    @Override
    public int getSize() {
        return this.taskList.size();
    }

    @Override
    public String getPrintableTasks() {
        return null;
    }
}
