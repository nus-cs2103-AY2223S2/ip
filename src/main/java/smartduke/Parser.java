package smartduke;

import smartduke.command.*;
import smartduke.task.Deadline;
import smartduke.task.Event;
import smartduke.task.Task;
import smartduke.task.ToDo;

import java.util.regex.Pattern;

public class Parser {
    /**
     * Enumerates the different types of string patterns of user commands available on SmartDuke.
     */
    enum CommandPattern {
        ADD_TODO("todo.*"),
        ADD_DEADLINE("deadline.*/by.*"),
        ADD_EVENT("event.*/from.*/to.*"),
        DELETE_TASK("delete[\\s]*[0-9]+[\\s]*"),
        MARK_TASK("mark[\\s]*[0-9]+[\\s]*"),
        UNMARK_TASK("unmark[\\s]*[0-9]+[\\s]*"),
        LIST_TASKS("list"),
        END_CHAT("bye");

        /**
         * The regex pattern of this Command.
         */
        private Pattern pattern;

        /**
         * Checks if the given string matches this Command.
         * @param userCommand The given string provided by the user.
         * @return True if the given string matches this Command.
         */
        public boolean match(String userCommand) {
            return this.pattern.matcher(userCommand).matches();
        }

        private CommandPattern(String regex) {
            this.pattern = Pattern.compile(regex);
        }
    }

    /**
     * Parses the user input command string.
     * @param userCommand The user input string.
     * @return The user command encapsulated in the form a Command object.
     * @throws DukeException If an invalid or unsupported command is provided by the user.
     */
    public static Command parse(String userCommand) throws DukeException {
        if (CommandPattern.ADD_TODO.match(userCommand)) {
            /* add todo task */
            String taskDesc = userCommand.substring(4).trim();
            Task todoTask = new ToDo(taskDesc);
            return new AddCommand(todoTask);
        } else if (CommandPattern.ADD_DEADLINE.match(userCommand)) {
            /* add deadline task */
            String[] parsedCommand = userCommand.substring(8).split("/by", -1);
            String taskDesc = parsedCommand[0].trim();
            String by = parsedCommand[1].trim();
            Task deadlineTask = new Deadline(taskDesc, by);
            return new AddCommand(deadlineTask);
        } else if (CommandPattern.ADD_EVENT.match(userCommand)) {
            /* add event task */
            String[] parsedCommand = userCommand.substring(5).split("/from|/to", -1);
            String taskDesc = parsedCommand[0].trim();
            String from = parsedCommand[1].trim();
            String to = parsedCommand[2].trim();
            Task eventTask = new Event(taskDesc, from, to);
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
        } else if (CommandPattern.END_CHAT.match(userCommand)) {
            /* End the session */
            return new ExitCommand();
        } else throw new DukeException("huh? i dont understand you...");

    }
}
