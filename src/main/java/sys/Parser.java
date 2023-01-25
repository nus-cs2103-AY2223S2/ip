package sys;

import command.*;
import exception.InvalidDateFormatException;
import task.TaskList;

/**
 * Represents a parser used to decode user input.
 */
public class Parser {
    TaskList tl;
    Storage storage;
    Ui ui;

    Parser(TaskList tl, Storage storage, Ui ui) {
        this.tl = tl;
        this.storage = storage;
        this.ui = ui;
    };

    /**
     * Converts a user input into a Command object.
     *
     * @param input The input line given by the user.
     * @return Command object representing the input.
     */
    public Command parse(String input) {
        // Handle control flow
        switch (input) {
            case "bye":
                // Exit Duke
                return new ExitCommand();
            case "list":
                // List tasks
                return new ListCommand();
            default:
                if (input.matches("occurs .*")) {
                    return new OccursCommand(input);
                } else if (input.matches("mark \\d+")) {
                    return new MarkCommand(input);
                } else if (input.matches("unmark \\d+")) {
                    return new UnmarkCommand(input);
                } else if (input.matches("delete \\d+")) {
                    return new DeleteCommand(input);
                } else  {
                    return new AddCommand(input);
                }
        }
    }
}
