package duke;

import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.ToDoTask;

import duke.exceptions.IndexDukeException;

/**
 * Represents a list of Task objects.
 * Includes different methods for user to keep track of his task progress.
 */
public class ToDoList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int toDoCount;

    public ToDoList() {
        //arr uses 1-indexing, 0 position placed with a dummy Task
        tasks.add(new ToDoTask("0index"));
        this.toDoCount = 0;
    }

    public int getToDoCount() {
        return this.toDoCount;
    }

    /**
     * Returns the Task object stored at the specified position on the ToDoList.
     *
     * @param index The position of the desired Task Object on the ToDoList.
     * @return The Task Object located on the given position.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= this.toDoCount).
     */
    public Task getTask(int index) throws IndexDukeException {
        if (index < 1 || index > toDoCount) {
            throw new IndexDukeException();
        }
        assert index > 0 : "index should be greater than 0";
        return this.tasks.get(index);
    }

    /**
     * Adds the given Task object to the back of the ToDoList
     *
     * @param task The Task object to be added into the ToDoList.
     */
    public void add(Task task) {
        ++this.toDoCount;
        tasks.add(task);
    }

    /**
     * Removes and return the Task object at the specified position on the ToDoList.
     *
     * @param index The position of the Task Object to be removed on the ToDoList.
     * @return The Task object removed from the ToDoList.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= this.toDoCount).
     */
    public Task delete(int index) throws IndexDukeException {
        if (index < 1 || index > toDoCount) {
            throw new IndexDukeException();
        }
        assert index > 0 : "index should be greater than 0";
        Task rm = tasks.get(index);
        tasks.remove(index);
        --this.toDoCount;
        return rm;
    }


    /**
     * Unmarks the Task Object at the specified position on the ToDoList.
     *
     * @param index The position of the Task Object to be unmarked on the ToDoList.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= this.toDoCount).
     */
    public void unmarkTask(int index) throws IndexDukeException {
        if (index < 1 || index > toDoCount) {
            throw new IndexDukeException();
        }
        assert index > 0 : "index should be greater than 0";
        tasks.get(index).markNotDone();
    }

    /**
     * Marks the Task Object at the specified position on the ToDoList.
     *
     * @param index The position of the Task Object to be unmarked on the ToDoList.
     * @throws IndexDukeException If the index is out of range (index < 1 || index >= this.toDoCount).
     */
    public void markTask(int index) throws IndexDukeException {
        if (index < 1 || index > toDoCount) {
            throw new IndexDukeException();
        }
        assert index > 0 : "index should be greater than 0";
        tasks.get(index).markDone();
    }

    public String find(String keyword) {
        String output = "";
        for (int i = 1; i <= this.toDoCount; i++) {
            if (tasks.get(i).contains(keyword)) {
                output = output + i + "." + tasks.get(i) + "\n";
            }
        }
        return output;
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
