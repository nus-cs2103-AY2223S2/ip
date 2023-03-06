package duke;

import duke.command.Command;
import duke.command.AddTaskCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import java.util.Map;
import java.util.HashMap;

/**
 * The Parser class that is used to take in and parse commands to be used for the correct purpose
 */
public class Parser {
    static final String VALID_COMMANDS = "bye, list, mark, unmark, delete, todo, deadline, event, find";
    static final String INVALID_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means. My valid commands are:\n"
            + VALID_COMMANDS;
    static final HashMap<String, String> CORRECT_FORMAT = new HashMap<>(Map.of(
            "list", "list",
            "bye", "bye",
            "todo", "todo THE TASK",
            "deadline", "deadline THE TASK /by yyyy-mm-ddThh:mm:ss",
            "event", "event THE TASK /from TIME /to TIME",
            "mark", "mark NUMBER",
            "unmark", "unmark NUMBER",
            "delete", "delete NUMBER",
            "find", "find WORDS"
    ));

    /**
     * Returns the correct Command given by the string input
     *
     * @param fullCommand String containing the input of the full command
     * @return cmd the correct type of Command
     * @throws DukeException if the command does not exist or is in the wrong format
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] words = fullCommand.split(" ");
        if (!CORRECT_FORMAT.containsKey(words[0])) {
            throw new DukeException(INVALID_COMMAND_MESSAGE);
        }
        Command cmd = new Command();
        try {
            switch (words[0]) {
            case "bye":
                cmd = new ExitCommand();
                break;
            case "list":
                cmd = new ListCommand();
                break;
            case "mark":
                cmd = new MarkCommand(words[1]);
                break;
            case "unmark":
                cmd = new UnmarkCommand(words[1]);
                break;
            case "delete":
                cmd = new DeleteCommand(words[1]);
                break;
            case "find":
                cmd = new FindCommand(words[1]);
                break;
            default:    // for tasks
                cmd = new AddTaskCommand(fullCommand);
            }
        } catch (Exception e) {
            throw new DukeException(correctFormat(words[0]);
        }
        return cmd;
    }
    private static String correctFormat(String typeOfTask) {
        return String.format("Please enter in the correct format:\n%s", CORRECT_FORMAT.get(typeOfTask));
    }
}
