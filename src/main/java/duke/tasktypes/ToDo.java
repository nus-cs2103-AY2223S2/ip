package duke.tasktypes;

import duke.DukeExceptions;

/**
 * Class which is more specific in nature than Task.
 */
public class ToDo extends Task {

    /**
     * Constructor to initialize a ToDo task object.
     *
     * @param taskName String which the ToDo task will be named after.
     * @throws DukeExceptions if the task name is empty.
     */
    public ToDo(String taskName) throws DukeExceptions {
        super(taskName);
        if (taskName.length() <= 0 || taskName.isBlank()) {
            throw new DukeExceptions("todo");
        }
    }

    /**
     * Function to print the ToDo task depending on whether it is done.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String toString() {
        String toReturn = "";
        if (this.isDone) {
            toReturn = "[T][X]" + this.getName();
        } else {
            toReturn = "[T][ ]" + this.getName();
        }
        return toReturn;
    }
}
