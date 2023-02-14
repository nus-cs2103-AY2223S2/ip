package duke.task;

import duke.DukeException;

/** Deals with tasks. */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Generates a new task.
     *
     * @param description Description of task.
     * @param isDone Status of task.
     */
    public Task(String description, boolean... isDone) {
        this.description = description;
        this.isDone = isDone.length > 0 && isDone[0];
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /** Changes status to done. */
    public void mark() {
        assert !isDone : "Task was already done";
        this.isDone = true;
    }

    /** Changes status to not done. */
    public void unmark() {
        assert isDone : "Task was not done";
        this.isDone = false;
    }

    /**
     * Returns task in saved data format.
     *
     * @param delimiter String separating fields.
     * @return Task in saved data format.
     */
    public String toSaveData(String delimiter) {
        return " " + delimiter
                + getStatusIcon()
                + delimiter
                + description;
    }

    /**
     * Loads task from given saved data.
     *
     * @param data Saved data of task.
     * @param delimiter String separating fields.
     * @return New task.
     * @throws DukeException If saved data is missing some fields.
     */
    public static Task load(String data, String delimiter) throws DukeException {
        try {
            String[] fields = data.split(delimiter, 3);
            String taskType = fields[0];
            boolean status = fields[1].equals("X");
            String description = fields[2];

            assert taskType.equals(" ") : "Task is of the wrong type";

            return new Task(description, status);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Saved data is missing some fields");
        }
    }

    /**
     * Compares this task to the specified task.
     *
     * @param task The task to compare this task against.
     * @return true if the given task is equivalent to this task, false otherwise.
     */
    public boolean equals(Task task) {
        boolean isSameDescription = task.description.equals(description);
        boolean isSameStatus = task.isDone == isDone;

        return isSameDescription && isSameStatus;
    }

    /**
     * Returns the task's details in string format.
     *
     * @return Task's details.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + description;
    }
}
