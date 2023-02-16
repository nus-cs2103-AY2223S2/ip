package duke;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Parses all user input into commands.
 */
public class Parser {
    private TaskList tasks;

    /**
     * Constructor to create a Parser object.
     *
     * @param tasks The TaskList.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the user input into an executable Command for Duke.
     *
     * @param input The user input
     * @return The Command parsed from the user input.
     */
    public Command parse(String input) throws DukeException {
        if (isFind(input)) {
            return new Find(input);
        } else if (isBye(input)) {
            return new Bye(input);
        } else if (isList(input)) {
            return new ListTasks(input);
        } else if (isMark(input, tasks.size())) {
            return new Mark(input);
        } else if (isUnMark(input, tasks.size())) {
            return new Unmark(input);
        } else if (isDelete(input, tasks.size())) {
            return new Delete(input);
        } else {
            if (isToDo(input)) {
                return new Add(input, "todo");
            } else if (isDeadline(input)) {
                return new Add(input, "deadline");
            } else if (isEvent(input)) {
                return new Add(input, "event");
            } else {
                throw new DukeException("Please input a valid command!");
            }
        }
    }

    /**
     * Checks if the user input is a find command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a find command.
     */
    public boolean isFind(String input) {
        return input.length() >= 6 && input.startsWith("find ");
    }

    /**
     * Checks if the user input is a bye command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a bye command.
     */
    public boolean isBye(String input) {
        return "bye".equals(input);
    }

    /**
     * Checks if the user input is a list command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a list command.
     */
    public boolean isList(String input) {
        return "list".equals(input);
    }

    /**
     * Checks if the user input is a mark command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a mark command.
     */
    public boolean isMark(String input, int listSize) {
        if (input.length() >=  6 && input.startsWith("mark ") && isNumeric(input.substring(5))) {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            return !(taskIndex < 0 || taskIndex > listSize - 1);
        }
        return false;
    }

    /**
     * Checks if the user input is an unmark command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a unmark command.
     */
    public boolean isUnMark(String input, int listSize) {
        if (input.length() >=  8 && input.startsWith("unmark ") && isNumeric(input.substring(7))) {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            return !(taskIndex < 0 || taskIndex > listSize - 1);
        }
        return false;
    }

    /**
     * Checks if the user input is a delete command.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a delete command.
     */
    public boolean isDelete(String input, int listSize) {
        if (input.length() >=  8 && input.startsWith("delete ") && isNumeric(input.substring(7))) {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            return !(taskIndex < 0 || taskIndex > listSize - 1);
        }
        return false;
    }

    /**
     * Checks if the user input is a ToDo task.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a ToDo task.
     */
    public boolean isToDo(String input) throws DukeException {
        if (input.length() >= 4 && input.startsWith("todo")) {
            if (input.equals("todo") || input.substring(4).isBlank()) {
                throw new DukeException("TODO needs a description!");
            } else return input.startsWith("todo ");
        }
        return false;
    }

    /**
     * Checks if the user input is a Deadline task.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is a Deadline task.
     */
    public boolean isDeadline(String input) throws DukeException {
        if (input.length() >= 8 && input.startsWith("deadline")) {
            if (input.equals("deadline") || input.substring(8).isBlank() ||
                    input.equals("deadline /by") || input.equals("deadline /by ")) {
                throw new DukeException("DEADLINE needs a description!");
            } else return input.contains(" /by ");
        }
        return false;
    }

    /**
     * Checks if the user input is an Event task.
     *
     * @param input The input String.
     * @return The boolean representing whether the user input is an Event task.
     */
    public boolean isEvent(String input) throws DukeException {
        if (input.length() >= 5 && input.startsWith("event")) {
            if (input.equals("event") || input.substring(5).isBlank()) {
                throw new DukeException("EVENT needs a description!");
            }
            int fromIndex = input.indexOf(" /from ");
            int toIndex = input.indexOf(" /to ");
            if (fromIndex + 7 > toIndex) {
                throw new DukeException("What are you saying?");
            }
            return true;
        }
        return false;
    }

    /**
     * Checks if a String is an Integer.
     *
     * @param strNum The input String.
     * @return The boolean representing if the String is an Integer.
     */
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }
}
