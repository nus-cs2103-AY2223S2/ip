package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UndoCommand;
import command.UnmarkCommand;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Parser deals with making sense of the user command and input date strings.
 */
public class Parser {
    /**
     * Enumerates the different types of string patterns of user commands available on SmartDuke.
     */
    private enum CommandPattern {
        ADD_TODO("todo.*"),
        ADD_DEADLINE("deadline.*/by.*"),
        ADD_EVENT("event.*/from.*/to.*"),
        DELETE_TASK("delete[\\s]*[0-9]+[\\s]*"),
        MARK_TASK("mark[\\s]*[0-9]+[\\s]*"),
        UNMARK_TASK("unmark[\\s]*[0-9]+[\\s]*"),
        FIND_TASK("find\\s.*"),
        LIST_TASKS("list"),
        UNDO("undo"),
        END_CHAT("bye");

        /**
         * The regex pattern of this Command.
         */
        private Pattern pattern;

        CommandPattern(String regex) {
            this.pattern = Pattern.compile(regex);
        }

        /**
         * Checks if the given string matches this Command.
         * @param userCommand The given string provided by the user.
         * @return True if the given string matches this Command.
         */
        public boolean match(String userCommand) {
            return this.pattern.matcher(userCommand).matches();
        }
    }

    /**
     * Converts user-input datetime string to LocalDateTime object.
     * @param dateTimeString The user-input datetime string.
     * @return The input datetime represented as a LocalDateTime object.
     * @throws DukeException If the input string is not of the supported format: yyyy-M-d HHmm.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d HHmm");
            return LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("You have provided an invalid date format. "
                    + "Make sure it is in yyyy-M-d HHmm format!");
        }
    }

    /**
     * Pretty-formats the input datetime for display.
     * @param dateTime The input datetime.
     * @return A datetime string in the format: d MMM yyyy h:mma.
     */
    public static String prettifyDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"));
    }

    /**
     * Undos the pretty-formatted datetime string into the user-input datetime string format.
     * @param dateTime The pretty-formatted datetime string
     * @return A datetime string in the format: yyyy-M-d HHmm.
     */
    public static String dePrettifyDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
    }

    /**
     * Parses the user input command string.
     * @param userCommand The user input string.
     * @return The user command represented as a Command object.
     * @throws DukeException If an invalid or unsupported command is provided by the user.
     */
    public static Command parse(String userCommand) throws DukeException {
        if (CommandPattern.ADD_TODO.match(userCommand)) {
            /* add todo task */
            String taskDesc = userCommand.substring(4).trim();
            if (taskDesc.length() == 0) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task todoTask = new ToDo(taskDesc);
            return new AddCommand(todoTask);
        } else if (CommandPattern.ADD_DEADLINE.match(userCommand)) {
            /* add deadline task */
            String[] parsedCommand = userCommand.substring(8).split("/by", -1);
            String taskDesc = parsedCommand[0].trim();
            String by = parsedCommand[1].trim();
            if (taskDesc.length() == 0) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            if (by.length() == 0) {
                throw new DukeException("OOPS!!! You need to indicate a deadline for this task...");
            }
            Task deadlineTask = new Deadline(taskDesc, Parser.parseDateTime(by));
            return new AddCommand(deadlineTask);
        } else if (CommandPattern.ADD_EVENT.match(userCommand)) {
            /* add event task */
            String[] parsedCommand = userCommand.substring(5).split("/from|/to", -1);
            String taskDesc = parsedCommand[0].trim();
            if (taskDesc.length() == 0) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String from = parsedCommand[1].trim();
            String to = parsedCommand[2].trim();
            if (from.length() == 0 || to.length() == 0) {
                throw new DukeException("OOPS!!! You need to indicate a start and end date/time for this task...");
            }
            Task eventTask = new Event(taskDesc, Parser.parseDateTime(from), Parser.parseDateTime(to));
            return new AddCommand(eventTask);
        } else if (CommandPattern.DELETE_TASK.match(userCommand)) {
            /* delete task */
            int taskNo = Integer.parseInt(userCommand.substring(6).trim());
            return new DeleteCommand(taskNo);
        } else if (CommandPattern.MARK_TASK.match(userCommand)) {
            /* mark task */
            int taskNo = Integer.parseInt(userCommand.substring(4).trim());
            return new MarkCommand(taskNo);
        } else if (CommandPattern.UNMARK_TASK.match(userCommand)) {
            /* unmark task */
            int taskNo = Integer.parseInt(userCommand.substring(6).trim());
            return new UnmarkCommand(taskNo);
        } else if (CommandPattern.LIST_TASKS.match(userCommand)) {
            /* list tasks */
            return new ListCommand();
        } else if (CommandPattern.FIND_TASK.match(userCommand)) {
            /* find tasks */
            String query = userCommand.substring(5);
            return new FindCommand(query);
        } else if (CommandPattern.UNDO.match(userCommand)) {
            /* undo previous command */
            return new UndoCommand();
        } else if (CommandPattern.END_CHAT.match(userCommand)) {
            /* End the session */
            return new ExitCommand();
        } else {
            throw new DukeException("Huh? I don't understand you...");
        }
    }
}
