package treebot.interfaces;

import treebot.tasks.Task;

import java.util.ArrayList;

public interface ITaskList {

    ArrayList<Task> getArrayListCopy();

    void addTask(Task task);

    void addTask(Task task, int index) throws IndexOutOfBoundsException;

    Task deleteTask(int index) throws IndexOutOfBoundsException;

    Task deleteTask(Task task);

    Task markTask(int index) throws IndexOutOfBoundsException;

    Task unmarkTask(int index) throws IndexOutOfBoundsException;

    ITaskList find(String keyword);

    int getSize();

    String getPrintableTasks();


}
