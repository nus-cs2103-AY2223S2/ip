package duke;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.ToDoTask;

import duke.exceptions.IndexDukeException;


public class ToDoList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int toDoCount;

    public ToDoList() {
        tasks.add(new ToDoTask("0index"));
        this.toDoCount = 0;
    }

    public int getToDoCount() {
        return this.toDoCount;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void add(Task task) {
        ++this.toDoCount;
        tasks.add(task);
    }

    public Task delete(int index) throws IndexDukeException {
        if (index < 1 || index > toDoCount) {
            throw new IndexDukeException();
        }
        Task rm = tasks.get(index);
        tasks.remove(index);
        --this.toDoCount;
        return rm;
    }


    public void unmarkTask(int index) throws IndexDukeException {
        if (index < 1 || index > toDoCount) {
            throw new IndexDukeException();
        }
        tasks.get(index).markNotDone();
    }

    public void markTask(int index) throws IndexDukeException {
        if (index < 1 || index > toDoCount) {
            throw new IndexDukeException();
        }
        tasks.get(index).markDone();
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= this.toDoCount; i++) {
            output = output + i + "." + tasks.get(i) + "\n";
        }
        return output;
    }
}
