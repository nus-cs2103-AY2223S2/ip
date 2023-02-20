package duke;

import duke.command.*;

public class Parser {
    /**
     * Returns true if user input is "bye".
     *
     * @param command user input.
     * @return true if user input is "bye", false otherwise.
     */
    public boolean checkEnd(String command) {
        return command.equalsIgnoreCase("bye");
    }

    /**
     * Returns the command being executed.
     * Returns an error if the input is not in the pre-determined commands.
     *
     * @param command the command to be executed.
     * @return the Command to be executed.
     * @throws DukeException if input is not in the pre-determined commands.
     */
    public static Command parse(String command) throws DukeException {
        switch(command.toLowerCase()) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        default:
            String[] commands = command.split(" ", 2);
            String firstWord = commands[0].toLowerCase();

            switch (firstWord) {
            case "mark":
                int i = Integer.parseInt(command.toLowerCase().replaceAll("mark ", "")) - 1;
                return new MarkCommand(i);
            case "unmark":
                int j = Integer.parseInt(command.toLowerCase().replaceAll("unmark ", "")) - 1;
                return new UnmarkCommand(j);
            case "delete":
                return new DeleteCommand(command);
            case "tag":
                return new TagCommand(command);
            case "find":
                return new FindCommand(command);
            case "todo":
                return new ToDoCommand(command);
            case "deadline":
                return new DeadlineCommand(command);
            case "event":
                return new EventCommand(command);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.");
            }
        }
    }
}

