package duke;

import duke.exceptions.IndexDukeException;
import duke.tasks.Task;
import duke.tasks.ToDoTask;

import java.util.ArrayList;

/**
 * Represents a list of Task objects.
 * Includes different methods for user to keep track of his task progress.
 */
public class ToDoList {
    private ArrayList<Task> arr = new ArrayList<>();
    private int toDoCount;

    public ToDoList() {
        //arr uses 1-indexing, 0 position placed with a dummy Task
        arr.add(new ToDoTask("0index"));
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


    /**
     * Unmarks the Task Object at the specified position on the ToDoList.
     *
     * @param ind The position of the Task Object to be unmarked on the ToDoList.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= this.toDoCount).
     */
    public void unmarkTask(int ind) throws IndexDukeException {
        if (ind < 1 || ind > toDoCount) {
            throw new IndexDukeException();
        }
        arr.get(ind).markNotDone();
    }

    /**
     * Marks the Task Object at the specified position on the ToDoList.
     *
     * @param ind The position of the Task Object to be unmarked on the ToDoList.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= this.toDoCount).
     */
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
