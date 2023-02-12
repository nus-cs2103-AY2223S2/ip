package duke.tasks;

import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidTaskTypeException;

/**
 * Task is an abstract class of ToDo, Event and Deadline
 * with a description and a completion status.
 */
public abstract class Task {
    private final String task;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param command Description of the Task.
     */
    public Task(String command) {
        this.task = command;
        this.isDone = false;
    }

    /**
     * Creates a Task object from a storage String.
     *
     * @param data The Storage String representing the Task.
     * @return The Task represented by the Storage String.
     */
    public static Task dataToTask(String data)
            throws InvalidTaskTypeException, InvalidDateException {
        Task task;

        //| is a metacharacter in regex. You'd need to escape it:
        String[] dataArray = data.split("\\|");
        //remove spaces in between
        for (int i = 0; i < dataArray.length; i++) {
            dataArray[i] = dataArray[i].trim();
        }

        switch(dataArray[0]) {
        case "[T]":
            task = new ToDo(dataArray[2]);
            break;
        case "[D]":
            task = new Deadline(dataArray[2], dataArray[3]);
            break;
        case "[E]":
            task = new Event(dataArray[2], dataArray[3], dataArray[4]);
            break;
        default:
            throw new InvalidTaskTypeException();
        }

        if (dataArray[1].equals("1")) {
            task.mark();
        }

        return task;
    }

    /**
     * Mark the Task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the Task as done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the boolean representation of the task completion.
     *
     * @return  True if task is done else return false.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return string task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        String checkbox = "[" + (isDone ? "X" : " ") + "]";
        return checkbox + " " + this.task;
    }

    public abstract String taskToData();
}
