package duke;

import duke.command.*;

public class Parser {
    /**
     * Returns true if user input is "bye".
     *
     * @param command user input
     * @return true if user input is "bye", false otherwise
     */
    public boolean checkEnd(String command) {
        return command.equalsIgnoreCase("bye");
    }

    public static Command parse(String command) throws DukeException {
        try {
            switch(command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            default:
                String[] commands = command.split(" ", 2);
                String firstWord = commands[0].toLowerCase();

                switch (firstWord) {
                case "mark":
                    int i = Integer.parseInt(command.replaceAll("mark ", "")) - 1;
                    return new MarkCommand(i);
                case "unmark":
                    int j = Integer.parseInt(command.replaceAll("unmark ", "")) - 1;
                    return new UnmarkCommand(j);
                case "delete":
                    return new DeleteCommand(command);
                case "find":
                    return new FindCommand(command);
                case "todo":
                    return new ToDoCommand(command);
                case "deadline":
                    return new DeadlineCommand(command);
                case "event":
                    return new EventCommand(command);
                default:
                    throw new DukeException("wrong");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ByeCommand();
    }
}

