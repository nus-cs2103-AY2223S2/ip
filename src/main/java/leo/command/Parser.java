package leo.command;

import java.util.Objects;

import leo.leoexception.LeoException;
import leo.storage.Storage;

/**
 * Represents a Parser make sense of commands from user input.
 */
public class Parser {

    private final Storage s;

    /**
     * Constructor to create a Parser object.
     *
     * @param s Storage to store Tasks from commands by user.
     */
    public Parser(Storage s) {
        this.s = s;
    }

    /**
     * Reads user inputs line by line.
     * @return Command input by user.
     */
    public Command readCommand(String input) throws LeoException {
        if (Objects.equals(input, "list")) {
            return new ListCommand(s, input);
        } else if (input.startsWith("mark")) {
            return new MarkCommand(s, input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(s, input);
        } else if (input.contains("delete")) {
            return new DeleteCommand(s, input);
        } else if (input.contains("find")) {
            return new FindCommand(s, input);
        } else if (input.contains("bye")) {
            return new ExitCommand(s, input);
        } else if (input.contains("view")) {
            return new ViewCommand(s, input);
        } else {
            return new AddCommand(s, input);
        }
    }

}
