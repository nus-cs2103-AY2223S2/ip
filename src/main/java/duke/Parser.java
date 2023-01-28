package duke;

import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/** Class that parses user input */
public class Parser {

    /** Constant Strings to represent each command supported by Duke */
    private static final String END_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";
    private static final String BY_INDICATOR = "/by";
    private static final String FROM_INDICATOR = "/from";
    private static final String TO_INDICATOR = "/to";

    /**
     * Parses the given command string.
     *
     * @param line String representing user command.
     * @return Command object representing user command.
     * @throws DukeException if given command string cannot be parsed.
     */
    public static Command parseCommand(String line) throws DukeException {
        if (line.equals(Parser.END_COMMAND)) {
            return new ExitCommand();
        } else if (line.equals(Parser.LIST_COMMAND)) {
            return new ListCommand();
        } else {
            return processCommand(line);
        }
    }

    /**
     * Parses multi-word command strings.
     *
     * @param line String representing multi-word command.
     * @return Command object representing multi-word command.
     * @throws DukeException if given multi-word command string cannot be parsed.
     */
    private static Command processCommand(String line) throws DukeException {
        String[] splitCommand = line.split(" ");
        String command = splitCommand[0];
        if (command.equals(Parser.MARK_COMMAND) || command.equals(Parser.UNMARK_COMMAND)
            || command.equals(Parser.DELETE_COMMAND)) {
            return processTask(command, splitCommand);
        } else if (command.equals(Parser.TODO_COMMAND) || command.equals(Parser.DEADLINE_COMMAND)
            || command.equals(Parser.EVENT_COMMAND)) {
            return addTask(command, line);
        } else if (command.equals(Parser.FIND_COMMAND)) {
            return processFind(line);
        } else {
            throw new DukeException("I don't know what that means. I must have forgotten.\n"
                    + "You can see what I remember using \'help\'.");
        }
    }

    /**
     * Parses command strings to do with altering the task list
     * or the state of tasks.
     *
     * @param command Command keyword.
     * @param splitCommand Command string split by spaces.
     * @return Command object representing user command.
     * @throws DukeException if given command string cannot be parsed.
     */
    private static Command processTask(String command, String[] splitCommand) throws DukeException {
        if (splitCommand.length == 1) {
            throw new DukeException("You didn't provide a task number. Unless it's invisible?!");
        } else if (splitCommand.length > 2) {
            throw new DukeException("That's too many numbers! I can only handle one...");
        }
        try {
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            if (command.equals(Parser.MARK_COMMAND)) {
                return new MarkCommand(taskIndex);
            } else if (command.equals(Parser.UNMARK_COMMAND)) {
                return new UnmarkCommand(taskIndex);
            } else {
                return new DeleteCommand(taskIndex);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("That's not a number! At least, I don't think it is...");
        }
    }

    /**
     * Parses command strings to do with adding tasks.
     *
     * @param command Command keyword.
     * @param line String representing user command.
     * @return Command object.
     * @throws DukeException if task cannot be created.
     */
    private static Command addTask(String command, String line) throws DukeException {
        Task task = null;
        switch (command) {
        case Parser.TODO_COMMAND:
            try {
                String description = line.split(Parser.TODO_COMMAND)[1].trim();
                if (description.isEmpty()) {
                    throw new DukeException("You didn't provide a description for this todo!"
                            + " What about something like pet every dog?\n"
                            + "That's something I want to do.");
                }
                task = new Todo(description);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("You didn't provide a description for this todo!"
                        + " What about something like pet every dog?\n"
                        + "That's something I want to do.");
            }
            break;
        case Parser.DEADLINE_COMMAND:
            try {
                String details = line.split(Parser.DEADLINE_COMMAND)[1].trim();
                String name = details.split(Parser.BY_INDICATOR)[0].trim();
                String deadline = details.split(Parser.BY_INDICATOR)[1].trim();
                if (name.isEmpty()) {
                    throw new DukeException("You didn't provide a description for this deadline!"
                            + " What is it that you need to get done?");
                } else if (deadline.isEmpty()) {
                    throw new DukeException("You didn't provide the deadline for this task! How about tomorrow?\n"
                            + "I need to get my homework done by then...");
                }
                task = new Deadline(name, deadline);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Something's missing here. Either the description or the date.");
            } catch (DateTimeParseException e) {
                throw new DukeException("I can't read that date. I only know dates in the format \'YYYY-MM-DD\'.");
            }
            break;
        case Parser.EVENT_COMMAND:
            try {
                String details = line.split(Parser.EVENT_COMMAND)[1].trim();
                String name = details.split(Parser.FROM_INDICATOR)[0].trim();
                String from = details.split(Parser.FROM_INDICATOR)[1].split(Parser.TO_INDICATOR)[0].trim();
                String to = details.split(Parser.FROM_INDICATOR)[1].split(Parser.TO_INDICATOR)[1].trim();
                if (name.isEmpty()) {
                    throw new DukeException("You didn't provide a description for this event!");
                } else if (from.isEmpty()) {
                    throw new DukeException("You didn't provide the start date of this event!");
                } else if (to.isEmpty()) {
                    throw new DukeException("You didn't provide the end date of this event!");
                }
                task = new Event(name, from, to);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Something's missing here. Either the description, the start date or the end date.");
            } catch (DateTimeParseException e) {
                throw new DukeException("I can't read these dates. I only know dates in the format \'YYYY-MM-DD\'.");
            }
            break;
        default:
            break;
        }
        return new AddCommand(task);
    }

    /**
     * Parses command strings to do with finding matching
     * tasks.
     *
     * @param line String representing user command.
     * @return Command object.
     * @throws DukeException if no keyword is provided.
     */
    private static Command processFind(String line) throws DukeException {
        try {
            String keyword = line.split(Parser.FIND_COMMAND)[1].trim();
            return new FindCommand(keyword);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I'm lost. You need to give me a keyword to look for!");
        }
    }

}
