package tasks;

import exceptions.IncompleteCommandException;
import formatters.StringUtils;

/**
 * Represents a basic task with no deadline or timeline.
 */
public class ToDo extends Task {

    /**
     * Constructor method for ToDo task.
     * @param description what the task is about.
     */
    private ToDo(String description) {
        super(description);
    }

    /**
     * Factory method to create a ToDo task based on commands.
     * @param commands string array of commands
     * @return a ToDo object based on commands.
     */
    public static ToDo create(String[] commands) {
        commands = StringUtils.removeWhiteSpace(commands);
        if (commands.length == 1) {
            throw new IncompleteCommandException(String.format("Hrrmmm. Not enough arguments, "
                    + "%s has. Hmm", "todo"), null);
        }
        String description = StringUtils.joinString(commands, 1, commands.length - 1);
        return new ToDo(description);
    }

    /**
     * Factory method to create a ToDo task based on data read from file.
     * @param description string array of commands
     * @param marked status of the task
     * @return a ToDo object based on commands.
     */
    public static ToDo create(String description, String marked) {
        ToDo newTask = new ToDo(description);
        if (marked.equals("1")) {
            newTask.markSilent();
        }
        return newTask;
    }

    /**
     * Concatenates the strings from [start, end] indices of commands array to form
     * required description of the task.
     * @param commands array of commands split by regex patterns like " "
     * @param start start index of commands array (inclusive)
     * @param end end index of commands array (inclusive)
     * @return a string description of a task
     */
    public static String buildDescription(String[] commands, int start, int end) {
        String description = "";
        for (int i = start; i < end; i++) {
            description += commands[i] + " ";
        }
        return description + commands[end];
    }

    /**
     * Checks the status of the task and logs to stdout.
     */
    @Override
    public void mark() {
        super.mark();
        System.out.printf(" [%s][%s] %s%n",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description);
    }

    /**
     * Unchecks the status of the task and logs to stdout.
     */
    @Override
    public void unmark() {
        super.unmark();
        System.out.printf(" [%s][%s] %s%n",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description);
    }

    /**
     * Method for child classes to return their type.
     * @return string type of task
     */
    @Override
    public String getTaskType() {
        return "T";
    }

    /**
     * String representation of the ToDo Task.
     * @return string representation of the ToDo Task.
     */
    @Override
    public String toString() {
        return String.format("[%s][%s] %s",
                this.getTaskType(),
                this.getStatusIcon(),
                this.description);
    }

    /**
     * Data representation of the ToDo Task into file.
     * @return data representation of the ToDo Task.
     */
    @Override
    public String writeTask() {
        return String.format("%s %d %s",
                this.getTaskType(),
                super.isCompleted() ? 1 : 0,
                this.description);
    }
}
