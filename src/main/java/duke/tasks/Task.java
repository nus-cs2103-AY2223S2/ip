package duke.tasks;

import duke.DukeException;

/**
 * Task encapsulates various tasks that can be done by the user.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * A Constructor for task.
     *
     * @param description Description of task.
     * @param isDone Completion status of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets status icon of task.
     *
     * @return String representation of the status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the isDone parameter to true.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone parameter to false.
     */
    public void markUnDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Converts the Task into its string representation.
     *
     * @return String representation of task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Converts boolean to int.
     *
     * @return Int representation of isDone.
     */
    public int isDoneToInt() {
        return isDone ? 1 : 0;
    }

    /**
     * Method that creates specific task given input data.
     *
     * @param data String formatted data of the Task that is retrieved from Storage.
     * @return Task created from data input.
     * @throws DukeException if task type is not recognised by duke.
     */
    public static Task loadTask(String data) throws DukeException {
        String[] dataSplit = data.trim().split("\\|", 4);
        boolean isDone = dataSplit[1].trim().equals("1");
        char taskType = dataSplit[0].charAt(0);
        String description = dataSplit[2].trim();
        switch (taskType) {
        case 'T':
            return new ToDo(description, isDone);
        case 'D':
            return new Deadline(description, dataSplit[3].trim(), isDone);
        case 'E':
            return new Event(description, dataSplit[3].split("to", 2)[0],
                    dataSplit[3].split("to", 2)[1], isDone);
        default:
            throw new DukeException("☹ OOPS!!! Unrecognised task type!");
        }
    }

    /**
     * Converts Task into string representation to be saved in storage.
     *
     * @return Formatted String representation of data.
     */
    public String saveFormat() {
        return String.format("%d | %s", isDoneToInt(), this.description);
    }

}
