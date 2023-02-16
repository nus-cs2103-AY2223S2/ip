package duke.task;

import duke.DukeException;

/** Deals with todo tasks. */
public class Todo extends Task {

    /**
     * Generates a new todo task.
     *
     * @param description Description of task.
     * @param isDone Status of task.
     */
    public Todo(String description, boolean... isDone) {
        super(description, isDone.length > 0 && isDone[0]);
    }

    /**
     * Returns task in saved data format.
     *
     * @param delimiter String separating fields.
     * @return Task in saved data format.
     */
    @Override
    public String toSaveData(String delimiter) {
        return "T" + delimiter
                + getStatusIcon()
                + delimiter
                + getDescription();
    }

    /**
     * Loads task from given saved data.
     *
     * @param data Saved data of task.
     * @param delimiter String separating fields.
     * @return New todo task.
     * @throws DukeException If saved data is missing some fields.
     */
    public static Todo load(String data, String delimiter) throws DukeException {
        try {
            String[] fields = data.split(delimiter, 3);
            String taskType = fields[0];
            boolean status = fields[1].equals("X");
            String description = fields[2];

            assert taskType.equals("T") : "Task is of the wrong type";

            return new Todo(description, status);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.getErrorTaskLoadMissingField();
        }
    }

    /**
     * Generates new todo task from given user input.
     *
     * @param input User's input.
     * @return New todo task.
     * @throws DukeException If input is missing some fields.
     */
    public static Todo generate(String input) throws DukeException {
        try {
            String[] fields = input.trim()
                    .split(" ", 2);
            String description = fields[1].trim();

            assert (fields[0].trim()
                    .equalsIgnoreCase("todo"))
                    : "The given input is of the wrong task type";

            return new Todo(description);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.getErrorTaskMissingField("todo");
        }
    }

    /**
     * Compares this task to the specified task.
     *
     * @param task The task to compare this task against.
     * @return true if the given task is equivalent to this task, false otherwise.
     */
    public boolean equals(Task task) {
        boolean isSameClass = task.getClass().equals(getClass());
        if (!isSameClass) {
            return false;
        }

        return super.equals(task);
    }

    /**
     * Returns the task's details in string format.
     *
     * @return Task's details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
