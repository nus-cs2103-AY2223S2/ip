package duke.task;

import duke.exception.InvalidFormatException;

import duke.Parser;

public abstract class Task {
    protected String classIcon;
    protected String description;
    protected boolean isDone;

    /**
     * A factory method that returns a Task based on logfile input
     *
     * @param type of Task
     * @param done isDone boolean value
     * @param text Description [Date] [Date] as String
     * @param parser Parser required to create task
     * @return Todo, Event or Date
     * @throws InvalidFormatException
     */
    public static Task factoryMethod(char type, char done, String text, Parser parser) throws InvalidFormatException {
        boolean isDone = done == 'X';

        switch (type) {
        case 'T':
            return new Todo(text, isDone);
        case 'D':
            return Deadline.factoryMethod(text, parser, isDone);
        case 'E':
            return Event.factoryMethod(text, parser, isDone);
        default:
            throw getInvalidFormatException();
        }
    }

    public static InvalidFormatException getInvalidFormatException() {
        return new InvalidFormatException(
                "Contents in log file cannot be understood. Consider rewriting or deleting it.");
    }

    /**
     * Returns a task
     *
     * @param description of the task
     * @param isDone if the task marked as done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Setter method of the task's isDone field
     *
     * @param isDone boolean value to set to
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", classIcon, getStatusIcon(), description);
    }

    /**
     * The string representation of a task. Any dates are changed to chosen output format
     *
     * @return String representation of Task
     */
    public String toStringLogFormat() {
        return String.format("[%s][%s] %s", classIcon, getStatusIcon(), description);
    }

    /**
     * Returns a character to show if task is done.
     *
     * @return "X" if done, else " "
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
}
