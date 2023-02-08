package duke.command;

/**
 * Class to parse the string command
 * into the Command class.
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class Parser {

    /**
     * Constructor of Parser.
     */
    public Parser() {
    }

    /**
     * Method that returns the right Command class
     * depending on the string input from the user.
     * @param echo Command from the user.
     * @return Command class
     */
    public Command parse(String echo) {
        String[] command = echo.split(" ");
        switch (command[0]) {
        case "bye":
            return new ByeCommand(command);
        case "list":
            return new ListCommand(command);
        case "mark":
            return new MarkCommand(command);
        case "unmark":
            return new UnmarkCommand(command);
        case "todo":
            return new ToDoCommand(command);
        case "event":
            return new EventCommand(command);
        case "deadline":
            return new DeadlineCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "find":
            return new FindCommand(command);
        default:
            return new NoCommand(command);
        }
    }

    /**
     * Method to parse tasks in storage file.
     * @param storageTask Line in storage txt file.
     * @return command for parser.
     */
    public Command parseStorage(String[] storageTask) {
        StringBuilder command = new StringBuilder();
        int spaceCount = 0;
        for (int i = 0; i < storageTask.length - 1; i++) {
            command.append(storageTask[i]);
            if (spaceCount < storageTask.length - 1) {
                command.append(" ");
                spaceCount += 1;
            }
        }
        return parse(command.toString());
    }
}
