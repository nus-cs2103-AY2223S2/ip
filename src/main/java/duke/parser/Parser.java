package duke.parser;

import duke.dukeException.DukeException;

/**
 * A parser object that makes sense of user commands.
 */
public class Parser {
    // The various possible commands
   public enum Command {
        TODO, DEADLINE, EVENT, LIST,
        MARK, UNMARK, DELETE, BYE, UNKNOWN
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

        switch (type) {
        case DEADLINE:
            input = input.replace("deadline ", "");
            if (input.equals("deadline")) {
                // Checks if deadline is empty.
                throw new DukeException("deadline");
            } else {
                return input.split(" /by ", 2);
            }
        case EVENT:
            input = input.replace("event ", "");
            if (input.equals("event")) {
                // Checks if event is empty.
                throw new DukeException("event");
            } else {
                String[] inputs = input.split(" /", 3);
                inputs[1] = inputs[1].replace("from ", "");
                inputs[2] = inputs[2].replace("to ", "");
                return inputs;
            }
        case TODO:
            input = input.replace("todo ", "");
            if (input.equals("todo")) {
                // Checks if todo is empty.
                throw new DukeException("todo");
            } else {
                return new String[]{input};
            }
        case MARK:
        case UNMARK:
        case DELETE:
            return input.split(" ", 2);
        default:
            return new String[]{""};
        }
    }
}
