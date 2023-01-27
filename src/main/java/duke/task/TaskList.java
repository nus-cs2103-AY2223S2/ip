package duke.task;

import duke.exception.EmptyDescException;

import java.io.IOException;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.io.FileWriter;

public class TaskList {
    protected ArrayList<Task> list;
    public TaskList(ArrayList<Task> taskList) {
        this.list = taskList;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void printList() {
        for (int i = 0; i < this.list.size(); i++) System.out.println((i+1) + "." + this.list.get(i));
    }

    public int listSize() {
        return this.list.size();
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public void markStatus(int index) {
        list.get(index - 1).markStatus(true);
    }

    public void unMarkStatus(int index) {
        list.get(index - 1).markStatus(false);
    }

    public Task addTasks(Task task) {
        this.list.add(task);
        return task;
    }

    public Task deleteTasks(int inputIndex) throws IOException {
        Task task = this.list.get(inputIndex - 1);
        this.list.remove(inputIndex - 1);

        return task;
    }

}
