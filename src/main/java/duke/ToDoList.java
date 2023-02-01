package duke;

import duke.exceptions.IndexDukeException;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

import java.util.ArrayList;


public class ToDoList {
    private ArrayList<Task> arr = new ArrayList<>(); //1-based index
    private int toDoCount;

    public ToDoList() {
        arr.add(new ToDoTask("0index")); //1-based index
        this.toDoCount = 0;
    }

    public int getToDoCount() {
        return this.toDoCount;
    }

    public Task getTask(int index) {
        return this.arr.get(index);
    }

    public void add(Task task) {
        ++this.toDoCount;
        arr.add(task);
    }

    public Task delete(int ind) throws IndexDukeException {
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        Task rm = arr.get(ind);
        arr.remove(ind);
        --this.toDoCount;
        return rm;
    }


    public void unmarkTask(int ind) throws IndexDukeException {
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        arr.get(ind).markNotDone();
    }

    public void markTask(int ind) throws IndexDukeException {
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        arr.get(ind).markDone();
    }

    public String find(String keyword) {
        String output = "";
        for (int i = 1; i <= this.toDoCount; i++) {
            if (arr.get(i).contains(keyword)) {
                output = output + i + "." + arr.get(i) + "\n";
            }
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= this.toDoCount; i++) {
            output = output + i + "." + arr.get(i) + "\n";
        }
        return output;
    }
}
