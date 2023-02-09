package james.jamesbot;

import james.command.AddCommand;
import james.command.AddMarkCommand;
import james.command.Command;
import james.command.DeleteCommand;
import james.command.ExitCommand;
import james.command.FindCommand;
import james.command.ListTasksCommand;


import james.exception.JamesException;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses the user input to determine which command to execute.
     *
     * @param userCommand The user input.
     * @return The command to be executed.
     * @throws JamesException If the command is unknown;
     *                      If the task description is empty.
     */
    public static Command parse(String userCommand) throws JamesException {
        if (userCommand.equals("bye")) {
            return new ExitCommand();
        } else if (userCommand.trim().equals("list")) {
            return new ListTasksCommand();
        } else if (userCommand.startsWith("mark") || userCommand.startsWith("unmark")) {
            return new AddMarkCommand(userCommand);
        } else if (userCommand.startsWith("todo")
                || userCommand.startsWith("deadline")
                || userCommand.startsWith("event")) {
            if (userCommand.trim().endsWith("todo")
                    || userCommand.trim().endsWith("deadline")
                    || userCommand.trim().endsWith("event")) {
                throw new JamesException("your task description is empty\n"
                        + "feed me a task description to get started!");
            }
            return new AddCommand(userCommand);
        } else if (userCommand.startsWith("delete")) {
            return new DeleteCommand(userCommand);
        } else if (userCommand.startsWith("find")) {
            return new FindCommand(userCommand);
        } else {
            throw new JamesException("sorry \nI do not understand what does that mean.");
        }
    }
}
