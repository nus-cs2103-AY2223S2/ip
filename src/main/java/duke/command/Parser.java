package duke.command;

import duke.exception.DukeException;

/**
 * Deals with making sense of the user input.
 */
public class Parser {

    /**
     * Deals with making sense of the user input.
     * Returns a command object after decoding the user input.
     * Throws exception if user input is not a recognizable command.
     *
     * @param input Contains the command name or other information to create command object.
     * @return The appropriate command object that can be executed.
     */
    public static Command parse(String input) throws DukeException {
        String[] commandString = input.split(" ");

        if (input.equalsIgnoreCase("bye")) {
            return new Command("bye");

        } else if (input.equalsIgnoreCase("list")) {
            return new Command("list");

        } else if (input.startsWith("mark")) {
            int index = Integer.parseInt(commandString[1]) - 1;
            return new Command("mark", index);

        } else if (input.startsWith("unmark")) {
            int index = Integer.parseInt(commandString[1]) - 1;
            return new Command("unmark", index);

        } else if (input.startsWith("delete")) {
            int index = Integer.parseInt(commandString[1]) - 1;
            return new Command("delete", index);

        } else if (input.startsWith("find")) {
            String keyword = input.substring(5);
            return new Command("find", keyword);

        } else if (input.startsWith("todo")) {
            String taskName = input.substring(5);
            return new Command("todo", taskName);

        } else if (input.startsWith("deadline")) {
            int dashIndex = input.indexOf("/");
            String taskName = input.substring(9, dashIndex);
            String by = input.substring(dashIndex + 4);
            return new Command("deadline", taskName, by);

        } else if (input.startsWith("event")) {
            int firstDashIndex = input.indexOf("/");
            int secondDashIndex = input.lastIndexOf("/");
            String taskName = input.substring(6, firstDashIndex);
            String from = input.substring(firstDashIndex + 6, secondDashIndex);
            String to = input.substring(secondDashIndex + 4);
            return new Command("event", taskName, from, to);

        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :(((");
        }
    }
}
