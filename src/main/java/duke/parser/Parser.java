package duke.parser;

import duke.dukeexception.DukeException;

/**
 * A parser object that makes sense of user commands.
 */
public class Parser {
    /**
     * Commands that can be used.
     */
    public enum Command {
        TODO, DEADLINE, EVENT, LIST, FIND,
        MARK, UNMARK, DELETE, BYE, UNKNOWN,
        UPDATE
    }

    /**
     * Parses the user input string into a set list of commands.
     *
     * @param input The string input by the user.
     * @return The respective Command.
     */
    public static Command parse(String input) {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.matches("todo(.*)")) {
            return Command.TODO;
        } else if (input.matches("deadline(.*)")) {
            return Command.DEADLINE;
        } else if (input.matches("event(.*)")) {
            return Command.EVENT;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.matches("mark(.*)")) {
            return Command.MARK;
        } else if (input.matches("unmark(.*)")) {
            return Command.UNMARK;
        } else if (input.matches("delete(.*)")) {
            return Command.DELETE;
        } else if (input.matches("find(.*)")) {
            return Command.FIND;
        } else if (input.matches("update(.*)")) {
            return Command.UPDATE;
        } else {
            return Command.UNKNOWN;
        }
    }

    /**
     * Returns the contents of the command.
     *
     * @param input The String input by the user.
     * @return A string array with the contents of the command.
     * @throws DukeException If command is empty.
     */
    public static String[] contents(String input) throws DukeException {
        Command type = parse(input);
        String output;
        switch (type) {
        case DEADLINE:
            output = input.replace("deadline ", "");
            if (output.equals("deadline")) {
                // Checks if deadline is empty.
                throw new DukeException("deadline");
            } else {
                return output.split(" /by ", 2);
            }
        case EVENT:
            output = input.replace("event ", "");
            if (output.equals("event")) {
                // Checks if event is empty.
                throw new DukeException("event");
            } else {
                String[] outputText = output.split(" /", 3);
                outputText[1] = outputText[1].replace("from ", "");
                outputText[2] = outputText[2].replace("to ", "");
                return outputText;
            }
        case TODO:
            output = input.replace("todo ", "");
            if (output.equals("todo")) {
                // Checks if todo is empty.
                throw new DukeException("todo");
            } else {
                return new String[]{output};
            }
        case UPDATE:
            String[] outputText = input.split(" ", 3);
            if (outputText.length < 3) {
                throw new DukeException("update");
            }
            return outputText;
        case MARK:
        case UNMARK:
        case FIND:
        case DELETE:
            return input.split(" ", 2);
        default:
            return new String[]{""};
        }
    }
}
