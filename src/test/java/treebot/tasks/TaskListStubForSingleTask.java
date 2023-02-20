package treebot.tasks;

import treebot.interfaces.ITaskList;

import java.util.ArrayList;

public class TaskListStubForSingleTask implements ITaskList {

    private Task task;

    @Override
    public ArrayList<Task> getArrayListCopy() {
        return null;
    }

    @Override
    public void addTask(Task task) {
        this.task = task;
    }

    @Override
    public void addTask(Task task, int index) {
        this.task = task;
    }

    @Override
    public Task deleteTask(int index) {
        Task deletedTask = task;
        this.task = null;
        return deletedTask;
    }

    @Override
    public Task deleteTask(Task task) {
        Task deletedTask = task;
        this.task = null;
        return deletedTask;
    }

    @Override
    public Task markTask(int index) {
        this.task.markAsDone();
        return this.task;
    }

    @Override
    public Task unmarkTask(int index) {
        this.task.markAsUndone();
        return this.task;
    }

    @Override
    public ITaskList find(String keyword) {
        return null;
    }

    @Override
    public int getSize() {
        return task == null ? 0 : 1;
    }

    @Override
    public String getPrintableTasks() {
        return this.task.toString();
    }
}
