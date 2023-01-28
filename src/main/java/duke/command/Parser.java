package duke.command;

import duke.exception.DukeException;

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
            String taskName = input.substring(5);
            return new Command("delete", taskName);

        } else if (input.startsWith("todo")) {
            String taskName = input.substring(5);
            return new Command("todo", taskName);

        } else if (input.startsWith("deadline")) {
            int dash_index = input.indexOf("/");
            String taskName = input.substring(9, dash_index);
            String by = input.substring(dash_index + 4);
            return new Command("deadline", taskName, by);

        } else if (input.startsWith("event")) {
            int first_dash_index = input.indexOf("/");
            int second_dash_index = input.lastIndexOf("/");
            String taskName = input.substring(6, first_dash_index);
            String from = input.substring(first_dash_index + 6, second_dash_index);
            String to = input.substring(second_dash_index + 4);
            return new Command("event", taskName, from, to);

        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
