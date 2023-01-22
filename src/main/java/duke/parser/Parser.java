package duke.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TaskCommand;

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
            command = new TaskCommand('T', fullCommand);
        } else if (lowercaseCommand.startsWith("deadline")) {
            command = new TaskCommand('D', fullCommand);
        } else if (lowercaseCommand.startsWith("event")) {
            command = new TaskCommand('E', fullCommand);
        } else if (lowercaseCommand.startsWith("delete")) {
            command = new DeleteCommand(fullCommand);
        } else if (lowercaseCommand.contains("mark")) {
            command = new MarkCommand(!lowercaseCommand.startsWith("unmark"), fullCommand);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

}
