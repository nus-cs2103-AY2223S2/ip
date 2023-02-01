package task;

import task.Task;

public class Todo extends Task {
    public Todo(String details) {
        super(details);
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[T] " + task;
    }

    /**
     * Returns the details of the to-do in a format that can be stored
     *  in the file for easy loading later.
     * @return the string to store in the file
     */
    @Override
    public String toStorageString() {
        return "T#" + super.toStorageString();
    }
}
