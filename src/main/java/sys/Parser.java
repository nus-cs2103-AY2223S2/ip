package sys;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.OccursCommand;
import command.UnmarkCommand;
import command.UpdateCommand;

/**
 * Represents a parser used to decode user input.
 */
public class Parser {

    /**
     * Constructor for Parser.
     */
    Parser() {};

    /**
     * Converts a user input into a Command object.
     *
     * @param input The input line given by the user.
     * @return Command object representing the input.
     */
    public Command parse(String input) {
        switch (input) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            if (input.matches("occurs .*")) {
                return new OccursCommand(input);
            } else if (input.matches("find .*")) {
                return new FindCommand(input);
            } else if (input.matches("mark \\d+")) {
                return new MarkCommand(input);
            } else if (input.matches("unmark \\d+")) {
                return new UnmarkCommand(input);
            } else if (input.matches("delete \\d+")) {
                return new DeleteCommand(input);
            } else if (input.matches("update \\d+ .*")) {
                return new UpdateCommand(input);
            } else {
                return new AddCommand(input);
            }
        }
    }
}