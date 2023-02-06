package duke.command;

import duke.exception.DukeException;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles parsing of user inputs to determine the command to execute.
 */
public class Parser {
    private final Map<String, Command> strToCommand;

    /**
     * Creates a Parser object.
     */
    public Parser() {
        strToCommand = new HashMap<String, Command>();

        strToCommand.put("todo", new ToDoCommand());
        strToCommand.put("deadline", new DeadlineCommand());
        strToCommand.put("event", new EventCommand());
        strToCommand.put("delete", new DeleteCommand());
        strToCommand.put("mark", new MarkCommand());
        strToCommand.put("unmark", new UnmarkCommand());
        strToCommand.put("find", new FindCommand());
        strToCommand.put("list", new ListCommand());
        strToCommand.put("archive", new ArchiveCommand());
        strToCommand.put("bye", new ByeCommand());
    }

    /**
     * Returns the Command object associated with the command specified in input.
     *
     * @param input The input which contains a command.
     * @return The Command object associated with the command specified in the input.
     * @throws DukeException Indicates that no known command was found in the input.
     */
    public Command getCommand(String input) throws DukeException {
        assert input != null;

        String op = input.split(" ")[0];
        Command command = strToCommand.get(op);

        if (command == null) {
            throw new DukeException("I've never heard of such a command!");
        } else {
            return command;
        }
    }

    /**
     * Returns true if the input contains a bye command. Otherwise, returns false.
     *
     * @param input The input which is to be checked for whether it contains a bye command.
     * @return True if the input contains a bye command. False otherwise.
     */
    public boolean isByeCommand(String input) {
        assert input != null;

        return input.split(" ")[0].equals("bye");
    }
}
