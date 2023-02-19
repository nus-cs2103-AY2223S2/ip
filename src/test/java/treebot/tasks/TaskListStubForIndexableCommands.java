package treebot.tasks;

import treebot.interfaces.ITaskList;

import java.util.ArrayList;

public class TaskListStubForIndexableCommands implements ITaskList {

    private final int size = 5;
    @Override
    public ArrayList<Task> getArrayListCopy() {
        return null;
    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public void addTask(Task task, int index) {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Task deleteTask(int index) {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }

        return new Todo("Dummy todo");
    }

    @Override
    public Task deleteTask(Task task) {
        return null;
    }

    @Override
    public Task markTask(int index) {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }

        return new Todo("Dummy todo");
    }

    @Override
    public Task unmarkTask(int index) {
        if (isIndexOutOfBounds(index)) {
            throw new IndexOutOfBoundsException();
        }

        return new Todo("Dummy todo");
    }

    @Override
    public ITaskList find(String keyword) {
        return null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getPrintableTasks() {
        return null;
    }

    private boolean isIndexOutOfBounds(int index) {
        return index < 0 || index >= 5;
    }

}
