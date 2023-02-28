package fea.parser;

import fea.commands.AddCommand;
import fea.commands.ByeCommand;
import fea.commands.Command;
import fea.commands.DeleteCommand;
import fea.commands.FindCommand;
import fea.commands.InvalidCommand;
import fea.commands.ListCommand;
import fea.commands.MarkCommand;
import fea.commands.ReminderCommand;

/**
 * Parser class that parses the user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate command to be executed.
     *
     * @param fullCommand The user input.
     * @return The appropriate command.
     */
    public static Command parse(String fullCommand) {
        String lowercaseCommand = fullCommand.toLowerCase();
        Command command;
        if (lowercaseCommand.equals("bye")) {
            command = new ByeCommand();
        } else if (lowercaseCommand.equals("list")) {
            command = new ListCommand();
        } else if (lowercaseCommand.startsWith("todo")) {
            command = new AddCommand('T', fullCommand);
        } else if (lowercaseCommand.startsWith("deadline")) {
            command = new AddCommand('D', fullCommand);
        } else if (lowercaseCommand.startsWith("event")) {
            command = new AddCommand('E', fullCommand);
        } else if (lowercaseCommand.startsWith("delete")) {
            command = new DeleteCommand(fullCommand);
        } else if (lowercaseCommand.contains("mark")) {
            command = new MarkCommand(!lowercaseCommand.startsWith("unmark"), fullCommand);
        } else if (lowercaseCommand.startsWith("find")) {
            command = new FindCommand(fullCommand);
        } else if (lowercaseCommand.startsWith("reminder")) {
            command = new ReminderCommand(fullCommand);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

}
