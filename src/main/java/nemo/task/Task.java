package nemo.task;

import nemo.exception.NemoException;

/**
 * Abstract class representing a task.
 *
 * @author Lian Kok Hai
 */
public abstract class Task {
    protected String taskName;
    protected String type;
    protected boolean isDone;

    /**
     * Constructs a task with given name.
     *
     * @param taskName
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Encodes a task into a String representation to save into text file.
     * Implementation within each specific class.
     *
     * @return Encoded String representation of task.
     */
    public abstract String encode();

    /**
     * Decodes a String representation of a task into a Task object.
     *
     * @param str Encoded String representation of task.
     * @return Decoded Task object.
     * @throws NemoException Thrown when invalid String representation given.
     */
    public static Task decode(String str) throws NemoException {
        String[] splitStr = str.split(" \\| ", 5);
        Task result = null;
        switch(splitStr[0]) {
        case "T":
            result = new ToDo(splitStr[2]);
            break;
        case "D":
            result = new Deadline(splitStr[2], splitStr[3]);
            break;
        case "E":
            result = new Event(splitStr[2], splitStr[3], splitStr[4]);
            break;
        default:
            throw new NemoException("Invalid encoded String encountered");
        }
        if (splitStr[1].equals("true")) {
            result.markDone();
        }
        return result;
    }

    /**
     * Returns String representation of task.
     * Example format - [T] [X] todo task name.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.isDone ? "[X]" : "[ ]", this.taskName);
    }
}
