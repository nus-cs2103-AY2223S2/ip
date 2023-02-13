package james.jamesbot;

import james.command.AddDeadlineCommand;
import james.command.AddEventCommand;
import james.command.AddMarkCommand;
import james.command.AddToDoCommand;
import james.command.AddUnmarkCommand;
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
        String lowerCaseUserCmd = userCommand.toLowerCase();
        if (lowerCaseUserCmd.equals("bye")) {
            return new ExitCommand();
        } else if (lowerCaseUserCmd.trim().equals("list")) {
            return new ListTasksCommand();
        } else if (lowerCaseUserCmd.startsWith("mark")) {
            return new AddMarkCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("unmark")) {
            return new AddUnmarkCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("todo")) {
            return new AddToDoCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("deadline")) {
            return new AddDeadlineCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("event")) {
            return new AddEventCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("delete")) {
            return new DeleteCommand(userCommand);
        } else if (lowerCaseUserCmd.startsWith("find")) {
            return new FindCommand(userCommand);
        } else {
            throw new JamesException("sorry \nI do not understand what does that mean.");
        }
    }
}

