package duke;

import duke.exceptions.IndexDukeException;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> arr = new ArrayList<>(); //1-based index
    private int toDoCount;

    /**
     * Creates an instance of a ToDoList which holds Task objects.
     */
    public ToDoList() {
        arr.add(new ToDoTask("0index")); //1-based index
        this.toDoCount = 0;
    }

    public int getToDoCount() {
        return this.toDoCount;
    }

    /**
     * Returns the Task object stored at the specified position on the ToDoList.
     *
     * @param ind The position of the desired Task Object on the ToDoList.
     * @return The Task Object located on the given position.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= this.toDoCount).
     */
    public Task getTask(int ind) throws IndexDukeException {
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        return this.arr.get(ind);
    }

    /**
     * Adds the given Task object to the back of the ToDoList
     *
     * @param task The Task object to be added into the ToDoList.
     */
    public void add(Task task) {
        ++this.toDoCount;
        arr.add(task);
    }

    /**
     * Removes and return the Task object at the specified position on the ToDoList.
     *
     * @param ind The position of the Task Object to be removed on the ToDoList.
     * @return The Task object removed from the ToDoList.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= this.toDoCount).
     */
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

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= this.toDoCount; i++) {
            output = output + i + "." + arr.get(i) + "\n";
        }
        return output;
    }
}
