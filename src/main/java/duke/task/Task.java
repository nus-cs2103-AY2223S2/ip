package duke.task;

import duke.exception.DukeException;

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

    /**
     * Encodes a task into a String representation to save into text file.
     * Implementation within each specific class.
     *
     * @return Encoded String representation of task.
     */
    abstract public String encode();

    /**
     * Decodes a String representation of a task into a Task object.
     *
     * @param str Encoded String representation of task.
     * @return Decoded Task object.
     * @throws DukeException Thrown when invalid String representation given.
     */
    public static Task decode(String str) throws DukeException {
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
        }
        if (splitStr[1].equals("true")) {
            result.markDone();
        }
        if (result == null) {
            throw new DukeException("Invalid encoded String encountered");
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