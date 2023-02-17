package userinteraction;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.FindTaskCommand;
import command.HelpCommand;
import command.InvalidCommand;
import command.ListCommand;
import command.MarkTaskCommand;

/**
 * Parses all the user input into corresponding commands to be executed.
 */
public class Parser {

    /**
     * Checks whether the commands are valid. If it is valid, return a corresponding
     * command.
     *
     * @param input The user input.
     * @return The corresponding command.
     */
    public static Command parse(String input) {
        if (input.trim().equals("bye")) {
            return new ExitCommand(input);
        } else if (input.trim().equals("list")) {
            return new ListCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkTaskCommand(input, true);
        } else if (input.startsWith("unmark")) {
            return new MarkTaskCommand(input, false);
        } else if (input.startsWith("todo")) {
            return new AddTodoCommand(input);
        } else if (input.startsWith("deadline")) {
            return new AddDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new AddEventCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteTaskCommand(input);
        } else if (input.startsWith("find")) {
            return new FindTaskCommand(input);
        } else if (input.trim().equals("help")) {
            return new HelpCommand(input);
        } else {
            return new InvalidCommand(input);
        }
    }
}
