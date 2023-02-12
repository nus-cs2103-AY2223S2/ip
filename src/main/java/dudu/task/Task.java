package dudu.task;

import dudu.exception.DuduException;
import dudu.exception.InvalidCommandException;

/**
 * Task class
 */
public abstract class Task {
    private String desc;
    private boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param desc Description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String desc, boolean isDone) {
        this.desc = desc.strip();
        this.isDone = isDone;
    }

    /**
     * Gets the type of task.
     *
     * @return Returns the type in String
     */
    public abstract String getType();

    /**
     * Gets the completion status of the task.
     *
     * @return '1' as done, '0' as undone.
     */
    public abstract String getStatus();

    /**
     * Gets the description of the task.
     *
     * @return Returns the description in String
     */
    public abstract String getDescription();

    /**
     * Gets the completion status of the task.
     *
     * @return 'True' as done; 'False' otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     * @throws DuduException If the task has already marked as done.
     */
    public void markAsDone() throws DuduException {
        if (isDone) {
            throw new DuduException("The task has already marked as DONE!!!", "Already marked");
        }
        isDone = true;
    }

    /**
     * Marks the task as undone.
     * @throws DuduException If the task has already marked as undone.
     */
    public void markAsUndone() throws DuduException {
        if (!isDone) {
            throw new DuduException("The task has already marked as UNDONE!!!", "Already marked");
        }
        isDone = false;
    }

    /**
     * Visual display of the task status.
     *
     * @return 'X' as done; ' ' otherwise/
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    /**
     * Concatenate task's type, status and description using ","
     *
     * @return String of task's information.
     */
    public String encode() {
        return getType() + " | " + getStatus() + " | " + getDescription();
    }

    /**
     * Separates the task's type, status and description.
     * @param input String of input containing task information; separate using "|".
     * @return A specific task containing the task information.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static Task decode(String input) throws InvalidCommandException {
        String[] taskInfo = input.split(" \\| ");
        String type = taskInfo[0];
        boolean status = taskInfo[1].equals("1");
        String name = taskInfo[2];
        if (type.equals("Deadline")) {
            String by = taskInfo[3];
            return new Deadline(name, by, status);
        } else if (type.equals("Event")) {
            String from = taskInfo[3];
            String to = taskInfo[4];
            return new Event(name, from, to, status);
        } else {
            return new Todo(name, status);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof Task) {
            Task task = (Task) object;
            return isDone == task.isDone
                    &&
                    getDescription().equals(task.getDescription())
                    &&
                    getType().equals(task.getType());
        } else {
            return false;
        }

    }
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.desc;
    }
}
