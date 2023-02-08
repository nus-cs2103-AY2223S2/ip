package duke.task;

import duke.command.AddDeadlineCommand;
import duke.command.AddTodoCommand;
import duke.command.AddEventCommand;
import duke.command.Command;
import duke.exception.CommandException;
import duke.exception.DescriptionException;
import duke.DateTimeFormat;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Task {
    protected String description;

    protected boolean isDone;

    /**
     * Constructor for a deadline object based on the attributes that it has
     * @param description string representing the description
     * @throws DescriptionException If description is empty
     */
    public Task(String description) throws DescriptionException {
        this.description = description;
        this.isDone = false;
        if (this.isEmpty()) {
            throw new DescriptionException();
        }
    }

    /**
     * Checks if description is empty
     * @return whether the description is empty
     */
    public boolean isEmpty() {
        return this.description == null || this.description.trim().isEmpty();
    }

    /**
     * Checks if the input string is a substring of the description
     * @param testStr input string to test
     * @return whether the input string is a substring of the description
     */
    public boolean isMatch(String testStr) {
        return this.description.contains(testStr);
    }

    /**
     * Representation if task is marked
     * @return String representation of whether the task is marked
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * String representation of the object
     * @return String representation of deadline
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " | " + this.description;
    }

    /**
     * Converts the task in string to a command object
     * @param strTask task in string to be converted
     * @return the specific command that the string represents
     * @throws CommandException If there is something wrong with creation of command
     */
    public static Command taskToCommand(String strTask) throws CommandException {
        if (strTask.startsWith("deadline")) {
            Pattern pattern = Pattern.compile("deadline (.+) /by (.+)");
            Matcher matcher = pattern.matcher(strTask);
            if (matcher.find()) {
                return new AddDeadlineCommand(matcher.group(1), matcher.group(2));
            }
        } else if (strTask.startsWith("todo")) {
            Pattern pattern = Pattern.compile("todo (.+)");
            Matcher matcher = pattern.matcher(strTask);
            if (matcher.find()) {
                return new AddTodoCommand(matcher.group(1));
            }
        } else if (strTask.startsWith("event")) {
            Pattern pattern = Pattern.compile("event (.+) /from (.+) /to (.+)");
            Matcher matcher = pattern.matcher(strTask);
            if (matcher.find()) {
                return new AddEventCommand(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        throw new CommandException();
    }

    /**
     * Converts the task in string to a Task object
     * @param strTask task in string to be converted
     * @return the specific task that the string represents
     * @throws CommandException If there is something wrong with creation of task
     */
    public static Task strToTask(String strTask) throws CommandException {
        Task result;
        String[] strings = strTask.split(" \\| ");
        if (strTask.startsWith("T")) {
            result = new ToDo(strings[2]);
        } else if (strTask.startsWith("D")) {
            result = new Deadline(strings[2], strings[3]);
        } else if (strTask.startsWith("E")) {
            String[] separatedBy = strings[3].split("-");
            result = new Event(strings[2], separatedBy[0], separatedBy[1]);
        } else {
            throw new CommandException();
        }
        try {
            if (strings[1].equals("X")) {
                result.markDone();
            }
            return result;
        } catch (Exception e) {
            throw new CommandException();
        }
    }

    protected static LocalDateTime getLocalDateTime(String strDate) {
        for (DateTimeFormat format : DateTimeFormat.values()) {
            try {
                return LocalDateTime.parse(strDate, format.formatter);
            } catch (DateTimeException dateTimeException) {
                // no time given
                try {
                    return LocalDate.parse(strDate, format.formatter).atStartOfDay();
                } catch (DateTimeException ignored) {}
            }
        }
        return null;
    }
}
